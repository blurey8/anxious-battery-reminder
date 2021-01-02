package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.view;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver.AirplaneModeChangedReceiver;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver.BatteryChangedReceiver;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver.NetworkChangedReceiver;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.broadcastreceiver.WifiScanReceiver;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.viewmodel.MainViewModel;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private TextView mBatteryLevelText;
    private TextView mAirplaneModeText;
    private TextView mNetworkConnectionText;
    private TextView mWifiText;
    private BroadcastReceiver mBatteryChangedReceiver;
    private BroadcastReceiver mAirplaneModeChangedReceiver;
    private BroadcastReceiver mNetworkChangedReceiver;
    private BroadcastReceiver mWifiScanReceiver;

    public static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 123;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBatteryLevelText = view.findViewById(R.id.batteryLevelText);
        mAirplaneModeText = view.findViewById(R.id.airplaneModeText);
        mNetworkConnectionText = view.findViewById(R.id.networkConnectionText);
        mWifiText = view.findViewById(R.id.wifiText);

        Context context = getContext();
        assert context != null;

        mBatteryChangedReceiver = new BatteryChangedReceiver(mBatteryLevelText, context);
        mAirplaneModeChangedReceiver = new AirplaneModeChangedReceiver(mAirplaneModeText, context);
        mNetworkChangedReceiver = new NetworkChangedReceiver(mNetworkConnectionText, context);
        context.registerReceiver(mBatteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        context.registerReceiver(mAirplaneModeChangedReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        context.registerReceiver(mNetworkChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        PermissionHelper.askLocationPermission(context, getActivity(), PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);

        mWifiScanReceiver = new WifiScanReceiver(mWifiText, getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getContext().registerReceiver(mWifiScanReceiver, intentFilter);

        Log.d("MAIN FRAGMENT", "Asking Permission");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("MAIN FRAGMENT", "NOT GRANTED YET");

            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
        }

        GLSurfaceView myGLSurfaceView = view.findViewById(R.id.triangleSurfaceView);
        myGLSurfaceView.setZOrderOnTop(true);
        myGLSurfaceView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("MAIN FRAGMENT", "onRequestPermissionsResult");
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("MAIN FRAGMENT", "PackageManager.PERMISSION_GRANTED");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Context context = getContext();
        assert context != null;
        context.unregisterReceiver(mBatteryChangedReceiver);
        context.unregisterReceiver(mAirplaneModeChangedReceiver);
        context.unregisterReceiver(mNetworkChangedReceiver);
        context.unregisterReceiver(mWifiScanReceiver);
    }
}
