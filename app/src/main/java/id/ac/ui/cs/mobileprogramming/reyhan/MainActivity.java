package id.ac.ui.cs.mobileprogramming.reyhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
}
