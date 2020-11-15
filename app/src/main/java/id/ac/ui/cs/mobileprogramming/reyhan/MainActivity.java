package id.ac.ui.cs.mobileprogramming.reyhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import id.ac.ui.cs.mobileprogramming.reyhan.service.BatteryDropReminderService;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        Intent service = new Intent(getApplicationContext(), BatteryDropReminderService.class);
        getApplicationContext().startService(service);
    }
}
