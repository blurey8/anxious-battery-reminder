package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
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
        prevLevel = 101;
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

        prevLevel = currentLevel;
    }
}
