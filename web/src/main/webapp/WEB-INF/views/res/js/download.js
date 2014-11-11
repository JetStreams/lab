/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
var UI = (function () {

    function createWebgl() {
        //setup progress bar
        $("#progress").progressbar({
            value: false
        });

        //setup dialog
        $('#dlg').puidialog({
            closable: false,
            minimizable: false,
            maximizable: false
        });

        $("#control").buttonset();
    }

    function showProgress() {
        //show dialog
        $('#dlg').puidialog('show');

        //start progress animation
        setInterval(function () {
            var val = $('#progress').puiprogressbar('option', 'value') + 10;
            $('#progress').puiprogressbar('option', 'value', val);
        }, 2000);
    }

    function loadModel(type) {
        showProgress();

        //load stl model
        Jetstreams.run(type, function () {
            $('#dlg').puidialog('hide');
        });
    }

    function updateModel(type) {
        showProgress();

        //load stl model
        Jetstreams.update(type, function () {
            $('#dlg').puidialog('hide');
        });
    }

    /** public visible */
    return {
        create: function (type) {
            createWebgl();
            loadModel(type);
        },
        update: function (type) {
            updateModel(type);
        }

    };
})(jQuery);



