var system = require('system');

if (system.args.length !== 2) {
    console.log('Usage: run-jasmine.js URL');
    phantom.exit(1);
}

/**
 * Wait until the test condition is true or a timeout occurs. Useful for waiting
 * on a server response or for a ui change (fadeIn, etc.) to occur.
 *
 * @param testFx javascript condition that evaluates to a boolean,
 * it can be passed in as a string (e.g.: "1 == 1" or "$('#bar').is(':visible')" or
 * as a callback function.
 * @param onReady what to do when testFx condition is fulfilled,
 * it can be passed in as a string (e.g.: "1 == 1" or "$('#bar').is(':visible')" or
 * as a callback function.
 * @param timeOutMillis the max amount of time to wait. If not specified, 3 sec is used.
 */
function waitFor(testFx, onReady, timeOutMillis) {
    var maxtimeOutMillis = timeOutMillis ? timeOutMillis : 3001, //< Default Max Timeout is 3s
            start = new Date().getTime(),
            condition = false,
            interval = setInterval(function () {
                if ((new Date().getTime() - start < maxtimeOutMillis) && !condition) {
                    // If not time-out yet and condition not yet fulfilled
                    condition = (typeof (testFx) === "string" ? eval(testFx) : testFx()); //< defensive code
                } else {
                    if (!condition) {
                        // If condition still not fulfilled (timeout but condition is 'false')
                        console.log("'waitFor()' timeout");
                        phantom.exit(1);
                    } else {
                        // Condition fulfilled (timeout and/or condition is 'true')
                        console.log("'waitFor()' finished in " + (new Date().getTime() - start) + "ms.");
                        typeof (onReady) === "string" ? eval(onReady) : onReady(); //< Do what it's supposed to do once the condition is fulfilled
                        clearInterval(interval); //< Stop this interval
                    }
                }
            }, 100); //< repeat check every 100ms
}
;

// Check for console message indicating jasmine is finished running
var doneRegEx = /^\d+ specs, (\d+) failure/;
var noReallyDoneRegEx = /^Finished in \d[\d\.]* second/;
var rc;

var page = require('webpage').create();

// Route "console.log()" calls from within the Page context
// to the main Phantom context (i.e. current "this")

page.onConsoleMessage = function (msg) {
    system.stdout.write(msg);
    var match = doneRegEx.exec(msg);
    if (match) {
        rc = match[1] === "0" ? 0 : 1;
        return;
    }
    match = noReallyDoneRegEx.exec(msg);
    if (match) {
        system.stdout.writeLine("");
        phantom.exit(rc);
    }
};

page.open(system.args[1], function (status) {
    if (status !== "success") {
        console.log("Couldn't load the page");
        phantom.exit();
    } else {
        waitFor(function () {
            return page.evaluate(function () {
                return document.body.querySelector('.symbolSummary .pending') === null
            });
        }, function () {
            var exitCode = page.evaluate(function () {
                console.log(document.body.innerText + '\n');
                var failed = document.body.querySelector('.alert > .failed');
                if (failed) {
                    console.log(failed.innerText + '\n');
                    return 1;
                } else {
//                    console.log(document.body.querySelector('.alert > .passed').innerText + '\n')
                    return 0;
                }
            });
            phantom.exit(exitCode);
        });
    }
});