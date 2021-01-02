package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.util.PhoneSettings;

public class NetworkChangedReceiver extends BroadcastReceiver {

    TextView mNetworkText;
    boolean isNetworkConnected;

    public NetworkChangedReceiver(TextView textView, Context context) {
        mNetworkText = textView;
        initStatus(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        isNetworkConnected = PhoneSettings.isNetworkConnected(context);
        Log.d("Inet Connection INTENT:", String.valueOf(isNetworkConnected));
        refreshText(context);
    }

    public void initStatus(Context context) {
        isNetworkConnected = PhoneSettings.isNetworkConnected(context);
        refreshText(context);
    }

    public void refreshText(Context context) {
        String status = isNetworkConnected ? context.getString(R.string.on) : context.getString(R.string.off);
        mNetworkText.setText(String.format(context.getString(R.string.network_connection), status));
    }
}
