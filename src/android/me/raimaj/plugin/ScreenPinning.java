package me.raimaj.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
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
    private static final String CLASS_ADMIN_RECEIVER = "me.raimaj.plugin.DefaultDeviceAdminReceiver";

    private CallbackContext callback = null;
    private Activity activity = null;
    private ActivityManager activityManager = null;
    private DevicePolicyManager dpm = null;
    private ComponentName dac = null;


    /**
     * Cordova: initialize()
     */
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        activity = cordova.getActivity();
        activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        dac = new ComponentName(activity.getPackageName(), CLASS_ADMIN_RECEIVER);
    }


    /**
     * Cordova: execute()
     */
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException
    {  
        callback = callbackContext;
        Boolean deviceAdmin = false;

        try {

            if (ACTION_ENTER_PINNED_MODE.equals(action)) {
                if (!args.isNull(0))
                    deviceAdmin = args.getBoolean(0);
                return enterPinnedMode(deviceAdmin);
            }
            else if (ACTION_EXIT_PINNED_MODE.equals(action)) {
                return exitPinnedMode();
            }
            
        }
        catch (Exception e) {
            callback.error(e.getMessage());
        }

        return false;
    }


    /**
     * enterPinnedMode()
     */
    private boolean enterPinnedMode(Boolean deviceAdmin)
    {
        try {

            if (deviceAdmin) {
                dpm = (DevicePolicyManager) activity.getSystemService(Context.DEVICE_POLICY_SERVICE);
                if (dpm.isDeviceOwnerApp(activity.getPackageName())) {
                    String[] packages = {activity.getPackageName()};
                   dpm.setLockTaskPackages(dac, packages);
                }    
            }

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