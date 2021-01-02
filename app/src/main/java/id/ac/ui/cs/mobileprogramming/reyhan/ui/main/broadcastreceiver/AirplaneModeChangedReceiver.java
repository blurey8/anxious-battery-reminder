package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.reyhan.R;

import static id.ac.ui.cs.mobileprogramming.reyhan.util.PhoneSettings.isAirplaneMode;

public class AirplaneModeChangedReceiver extends BroadcastReceiver {

    TextView mAirplaneModeText;
    boolean isAirplaneMode;

    public AirplaneModeChangedReceiver(TextView textView, Context context) {
        mAirplaneModeText = textView;
        initStatus(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        isAirplaneMode = intent.getBooleanExtra("state", false);
        Log.d("AIRPLANE MODE INTENT:", String.valueOf(isAirplaneMode));
        refreshText(context);
    }

    public void initStatus(Context context) {
        isAirplaneMode = isAirplaneMode(context);
        Log.d("AIRPLANE MODE SYSTEM:", String.valueOf(isAirplaneMode));
        refreshText(context);
    }

    public void refreshText(Context context) {
        String status = isAirplaneMode ? context.getString(R.string.on) : context.getString(R.string.off);
        mAirplaneModeText.setText(String.format(context.getString(R.string.airplane_mode), status));
    }
}
