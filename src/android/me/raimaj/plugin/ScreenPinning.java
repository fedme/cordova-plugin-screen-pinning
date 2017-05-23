package me.raimaj.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;


public class ScreenPinning extends CordovaPlugin {

    private static final String ACTION_ENTER_PINNED_MODE = "enterPinnedMode";
    private static final String ACTION_EXIT_PINNED_MODE = "exitPinnedMode";
    private CallbackContext callback = null;
    private Activity activity = null;
    private ActivityManager activityManager = null;


    /**
     * Cordova: execute()
     */
    @override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException
    {
        activity = cordova.getActivity();
        activityManager = activity.getSystemService(Context.ACTIVITY_SERVICE);
        callback = callbackContext;

        switch (action) {
            case ACTION_ENTER_PINNED_MODE:
                return enterPinnedMode();
                break;
            case ACTION_EXIT_PINNED_MODE:
                return exitPinnedMode();
                break;
            default: 
                return false;
                break;
            }
    }


    /**
     * enterPinnedMode()
     */
    private boolean enterPinnedMode()
    {
        try {
            // activity not already pinned...
            if (!activityManager.isInLockTaskMode()) {
                activity.startLockTask();
                callback.success();
                return true;
            }
            // activity already pinned...
            callback.error("Activity is already pinned");
            return false;  
        }
        catch (Exception e) {
            callback.error(e.getMessage());
            return false;
        }    
    }


    /**
     * exitPinnedMode()
     */
    private boolean exitPinnedMode()
    {
        try {
            // activity is pinned...
            if (activityManager.isInLockTaskMode()) {
                activity.stopLockTask();
                callback.success();
                return true;
            }
            // activity is not pinned...
            callback.error("Activity is already unpinned");
            return false;  
        }
        catch (Exception e) {
            callback.error(e.getMessage());
            return false;
        }    
    }

}