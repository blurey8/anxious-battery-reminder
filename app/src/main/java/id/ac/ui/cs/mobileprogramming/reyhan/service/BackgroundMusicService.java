package id.ac.ui.cs.mobileprogramming.reyhan.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import id.ac.ui.cs.mobileprogramming.reyhan.R;

public class BackgroundMusicService extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MUSIC", "ON START ==========");
        mediaPlayer = MediaPlayer.create(this, R.raw.ekgdanger);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;
    }


    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MUSIC", "ON DESTROY ==========");
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
