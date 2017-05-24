/**
 * ScreenPinning Cordova plugin
 */

var exec = require('cordova/exec');

var ScreenPinning = {

    /**
     * enterPinnedMode()
     */
    enterPinnedMode: function(successCallback, errorCallback, deviceAdmin) {
        deviceAdmin = deviceAdmin || false;
        exec(successCallback, errorCallback, "ScreenPinning", "enterPinnedMode", [deviceAdmin]);
    },

    /**
     * exitPinnedMode()
     */
    exitPinnedMode: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "ScreenPinning", "exitPinnedMode", []);
    }

};

module.exports = ScreenPinning; 
