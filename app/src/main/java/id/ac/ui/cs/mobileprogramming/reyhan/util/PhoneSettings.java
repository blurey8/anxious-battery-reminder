package id.ac.ui.cs.mobileprogramming.reyhan.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

public class PhoneSettings {
    /**
     * Gets the state of Airplane Mode.
     *
     * @param context
     * @return true if enabled.
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneMode(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public static boolean isPowerSaving(Context context) {
        boolean powerSaveMode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (powerManager != null) {
                powerSaveMode = powerManager.isPowerSaveMode();
            }
            Log.d("powerSaveMode", String.valueOf(powerSaveMode));
        }
        // TODO: Support API < Lollipop
        return powerSaveMode;
    }
}
