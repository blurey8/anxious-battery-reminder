package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class WifiScanReceiver extends BroadcastReceiver {

    TextView mWifiText;
    //    boolean isNetworkConnected;
    int numOfWifiFound;
    WifiManager wifiManager;
    List<ScanResult> results;

    public WifiScanReceiver(TextView textView, Context context) {
        mWifiText = textView;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        initStatus(context);
        Log.d(TAG, "FROM WifiScanReceiver");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "FROM ON_RECEIVE");
        boolean success = intent.getBooleanExtra(
                WifiManager.EXTRA_RESULTS_UPDATED, false);
        if (success) {
            scanSuccess();
        } else {
            Log.d(TAG, "FROM EXTRA_RESULTS_UPDATED");
            scanFailure();
        }

        refreshText(context);
    }

    private void scanSuccess() {
        Log.d(TAG, "FROM scanSuccess");
        List<ScanResult> newResults = wifiManager.getScanResults();
        Log.d(TAG, String.valueOf(newResults.size()));
        if (results != null && newResults.size() == results.size()) {
            results = newResults;
            Log.d(TAG, "Still " + results.size());
            return;
        }

        results = newResults;

        Log.d(TAG, "SCAN SUCCEED | " + results.size() + " found");
        for (final ScanResult result : results) {
            Log.d(TAG, result.SSID);
        }
    }

    private void scanFailure() {
        Log.d(TAG, "SCAN FAILED");
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult result : results) {
            Log.d(TAG, result.toString());
        }
    }


    public void initStatus(Context context) {
        numOfWifiFound = 0;
        scanSuccess();
        refreshText(context);
    }

    public void refreshText(Context context) {
        mWifiText.setText(String.format("%s wifi hotspot found", String.valueOf(numOfWifiFound)));
    }
}
