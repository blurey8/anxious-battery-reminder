package id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.viewmodel.ReminderModeViewModel;

public class MySettingsFragment extends PreferenceFragmentCompat {

    private ReminderModeViewModel mReminderModeViewModel;

    public static Fragment newInstance() {
        return new MySettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mReminderModeViewModel = ViewModelProviders.of(this).get(ReminderModeViewModel.class);

        final ListPreference listPreference = findPreference("mode_preference");

        mReminderModeViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<ReminderMode>>() {
            @Override
            public void onChanged(@Nullable final List<ReminderMode> reminderModes) {
                CharSequence[] modeNames = new CharSequence[reminderModes.size()];
                CharSequence[] modeRanges = new CharSequence[reminderModes.size()];
                for (int i = 0; i < reminderModes.size(); i++) {
                    modeNames[i] = reminderModes.get(i).getName();
                    modeRanges[i] = String.valueOf(reminderModes.get(i).getRange());
                }

                listPreference.setEntries(modeNames);
                listPreference.setEntryValues(modeRanges);
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.my_preferences, rootKey);
    }
}