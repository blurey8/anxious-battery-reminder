package id.ac.ui.cs.mobileprogramming.reyhan.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;


@Database(entities = {ReminderMode.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "anxious_battery_reminder";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .addCallback(populateDb(context))
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    private static Callback populateDb(final Context context) {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).reminderModeDao().insertAll(ReminderMode.getPopulatedData());
                    }
                });
            }
        };
    }

    public abstract ReminderModeDao reminderModeDao();
}