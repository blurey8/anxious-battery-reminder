package id.ac.ui.cs.mobileprogramming.reyhan;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;
import id.ac.ui.cs.mobileprogramming.reyhan.data.repository.AppDatabase;
import id.ac.ui.cs.mobileprogramming.reyhan.data.repository.AppExecutors;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.SettingsFragment;


public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SETTINGS-ACTIVITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.settings_container, SettingsFragment.newInstance())
                    .commitNow();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        final AppDatabase appDb = AppDatabase.getInstance(this);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<ReminderMode> reminderModes = appDb.reminderModeDao().getAll();
                for (ReminderMode reminderMode : reminderModes) {
                    Log.d(TAG, reminderMode.getName());
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
