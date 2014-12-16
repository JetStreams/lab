/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
var UI = (function() {

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
        create: function() {
            createUpload();
        }
    };
})($, PUI);



