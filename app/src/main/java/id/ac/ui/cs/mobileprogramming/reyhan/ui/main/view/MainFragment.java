package id.ac.ui.cs.mobileprogramming.reyhan.ui.main.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.viewmodel.MainViewModel;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private TextView mBatteryLevelText;
    private TextView mAirplaneModeText;
    private TextView mNetworkConnectionText;
    private BroadcastReceiver mBatteryChangedReceiver;
    private BroadcastReceiver mAirplaneModeChangedReceiver;
    private BroadcastReceiver mNetworkChangedReceiver;


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

        Context context = getContext();
        assert context != null;

        mBatteryChangedReceiver = new BatteryChangedReceiver(mBatteryLevelText, context);
        mAirplaneModeChangedReceiver = new AirplaneModeChangedReceiver(mAirplaneModeText, context);
        mNetworkChangedReceiver = new NetworkChangedReceiver(mNetworkConnectionText, context);
        context.registerReceiver(mBatteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        context.registerReceiver(mAirplaneModeChangedReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        context.registerReceiver(mNetworkChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
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
    }
}
