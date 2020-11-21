package id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.viewmodel.ReminderModeViewModel;

public class MySettingsFragment extends PreferenceFragmentCompat {
    public static Fragment newInstance() {
        return new MySettingsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.my_preferences, rootKey);
        // Get the ViewModel.
        ReminderModeViewModel model = new ViewModelProvider(this).get(ReminderModeViewModel.class);

//        // Create the observer which updates the UI.
//        final Observer<String> nameObserver = new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable final String newName) {
//                // Update the UI, in this case, a TextView.
//                nameTextView.setText(newName);
//            }
//        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
//        model.getCurrentName().observe(this, nameObserver);
        List<ReminderMode> reminderModes = model.getAll().getValue();
        final ListPreference listPreference = (ListPreference) findPreference("mode_preference");

//        listPreference.setEntries();

    }
}