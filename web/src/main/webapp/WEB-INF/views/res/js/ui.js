/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
var UI = (function() {

    function createWebgl() {
        //setup progress bar
        //TODO: it might be better to use jquery's indeterminate 
        //progressbar when loading takes too long
        $("#progress").puiprogressbar({
            labelTemplate: 'Hold tight...'
        });
        //setup dialog
        $('#dlg').puidialog({
            closable: false,
            minimizable: false,
            maximizable: false
        });
        //show dialog
        $('#dlg').puidialog('show');

        //start progress animation
        setInterval(function() {
            var val = $('#progress').puiprogressbar('option', 'value') + 10;
            $('#progress').puiprogressbar('option', 'value', val);
        }, 2000);
    }

    function createUpload() {
        $('#panel').puipanel();
        $('#browse-click').puibutton();
        $('#upload').puibutton({
            icon: 'ui-icon-transferthick-e-w'
        });

        var intervalFunc = function() {
            var type = $('#file-type').val();
            if (type !== "undefined") {
                type = type.replace("C:\\fakepath\\", "");
            }
            $('#file-name').html(type);
        };
        $('#browse-click').on('click', function() {
            $('#file-type').click();
            setInterval(intervalFunc, 1);
            return false;
        });
    }


    /** public visible */
    return {
        webgl: function(type) {
            createWebgl();

            //load stl model
            Jetstreams.run('download/'+type, function() {
                $('#dlg').puidialog('hide');
            });
        },
        upload: function() {
            createUpload();
        }
    };
})(jQuery);



