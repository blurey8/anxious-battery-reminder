package id.ac.ui.cs.mobileprogramming.reyhan.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.reyhan.R;

import static android.content.ContentValues.TAG;

public class BatteryDropReminderService extends Service {
    private BroadcastReceiver mBatteryChangedReceiver;
    private NotificationManager mNotificationManager;
    private int mBatteryLevelBefore;
    private NotificationCompat.Builder mBuilder;
    private final String CHANNEL_ID = "abr_channel";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Context appContext = getApplicationContext();
        mBuilder = getNotificationBuilder(appContext);
        createNotificationChannel(appContext);
        registerBatteryChangedReceiver();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBatteryChangedReceiver != null) {
            Log.d(TAG, "SERVICE DESTROYED");
            unregisterReceiver(mBatteryChangedReceiver);
            mBatteryChangedReceiver = null;
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(Context context) {
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher) //
                .setContentTitle("Charge HP-mu!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private Notification getNotification(int batteryLevelCurrent) {
        String message = "Apa yakin tidak mau nge-charge? Udah tinggal " + batteryLevelCurrent + "% tuh";
        return mBuilder.setContentText(message).build();
    }

    private void registerBatteryChangedReceiver() {
        mBatteryChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "ACTION_BATTERY_CHANGED");
                int batteryLevelCurrent = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                Log.d(TAG, batteryLevelCurrent + "?" + mBatteryLevelBefore);
                if (batteryLevelCurrent < mBatteryLevelBefore) {
                    String message = "Apa yakin tidak mau nge-charge? Udah tinggal " + batteryLevelCurrent + "% tuh";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    Notification notification = getNotification(batteryLevelCurrent);
                    mNotificationManager.notify(batteryLevelCurrent, notification);
                }
                mBatteryLevelBefore = batteryLevelCurrent;
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent battery = registerReceiver(mBatteryChangedReceiver, filter);
        mBatteryLevelBefore = battery.getIntExtra(BatteryManager.EXTRA_LEVEL, 101);
    }

    private void createNotificationChannel(@NonNull Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Battery Reminder"; //getString(R.string.channel_name);
            String description = "Constantly remind user to charge everytime battery drops by 1%";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            mNotificationManager = context.getSystemService(NotificationManager.class);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(channel);
        }
    }
}