package id.ac.ui.cs.mobileprogramming.reyhan.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

public class PermissionHelper {
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 123;

    public static void askLocationPermission(Context context, Activity activity, int requestCode) {
        Log.d("PermissionHelper", "askLocationPermission");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("PermissionHelper", "NOT GRANTED YET");
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    requestCode);
        }
    }

    public static void askLocationPermission(Context context, Activity activity) {
        askLocationPermission(context, activity, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
    }
}