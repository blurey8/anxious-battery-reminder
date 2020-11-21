package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.reyhan.R;

public class BatteryChangedReceiver extends BroadcastReceiver {
    TextView mBatteryLevelText;

    public BatteryChangedReceiver(TextView textView) {
        mBatteryLevelText = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        mBatteryLevelText.setText(String.format(context.getString(R.string.battery_level), String.valueOf(level)));
    }
}
