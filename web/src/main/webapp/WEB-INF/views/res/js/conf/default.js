require.config({
    baseUrl: './js',
    paths: {
        jquery: "jquery/jquery-1.11.1.min",
        jqueryui: "jquery/jquery-ui.min",
        primeui: "primeui/primeui-1.1.min"
    },
    shim: {
        primeui: {
            deps: ["jquery", "jqueryui"],
            exports: "$"
        }
    }
});

require(["primeui"], function ($) {

    $('#panel').puipanel();

    var initUpload = function () {
        var browse = $('#browse-click');
        //continue only if the browse button exists
        if (browse.length) {
            browse.puibutton();
            $('#upload').puibutton({
                icon: 'ui-icon-transferthick-e-w'
            });

            var intervalFunc = function () {
                var type = $('#file-type').val();
                if (type !== "undefined") {
                    type = type.replace("C:\\fakepath\\", "");
                }
                $('#file-name').html(type);
            };
            $('#browse-click').on('click', function () {
                $('#file-type').click();
                setInterval(intervalFunc, 1);
                return false;
            });
        }
    }

    initUpload();

});