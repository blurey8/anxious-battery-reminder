package id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;
import id.ac.ui.cs.mobileprogramming.reyhan.data.repository.ReminderModeRepository;

public class ReminderModeViewModel extends AndroidViewModel {
    private ReminderModeRepository mRepository;
    private LiveData<List<ReminderMode>> mReminderModes;

    public ReminderModeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ReminderModeRepository(application);
        mReminderModes = mRepository.getAll();
    }

    public LiveData<List<ReminderMode>> getAll() {
        return mReminderModes;
    }

    public LiveData<List<ReminderMode>> getAllModeName() {
        return mReminderModes;
    }

    public LiveData<List<ReminderMode>> getAllModeRange() {
        return mReminderModes;
    }

    public void insert(ReminderMode reminderMode) {
        mRepository.insert(reminderMode);
    }
}