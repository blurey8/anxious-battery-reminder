package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.BatteryManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.service.BackgroundMusicService;

public class BatteryChangedReceiver extends BroadcastReceiver {
    TextView mBatteryLevelText;
    Intent musicService;
    int prevLevel;

    public BatteryChangedReceiver(TextView textView, Context context) {
        mBatteryLevelText = textView;
        musicService = new Intent(context, BackgroundMusicService.class);
        prevLevel = -1;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        mBatteryLevelText.setText(
                String.format(context.getString(R.string.battery_level), String.valueOf(currentLevel)));

        if ((currentLevel <= 20) && (prevLevel > 20)) {
            context.startService(musicService);
        } else if ((currentLevel > 20) && (prevLevel <= 20)) {
            context.stopService(musicService);
        }

        GLSurfaceView triangleSurfaceView = ((Activity) context).findViewById(R.id.triangleSurfaceView);
        Log.d("Battery", String.valueOf(isNumberDecrease(currentLevel, prevLevel)));
        if (isNumberDecrease(currentLevel, prevLevel)) {
            triangleSurfaceView.setVisibility(View.VISIBLE);
            SystemClock.sleep(2000);
            triangleSurfaceView.setVisibility(View.INVISIBLE);
        }

        prevLevel = currentLevel;
    }

    static {
        System.loadLibrary("native-lib");
    }

    private native boolean isNumberDecrease(int numBefore, int numAfter);
}
