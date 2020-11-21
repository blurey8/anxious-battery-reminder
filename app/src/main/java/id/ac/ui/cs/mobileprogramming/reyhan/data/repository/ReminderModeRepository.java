package id.ac.ui.cs.mobileprogramming.reyhan.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;

public class ReminderModeRepository {

    private ReminderModeDao mReminderModeDao;
    private LiveData<List<ReminderMode>> mReminderModeList;

    public ReminderModeRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mReminderModeDao = db.reminderModeDao();
        mReminderModeList = mReminderModeDao.getAll();
    }

    public LiveData<List<ReminderMode>> getAll() {
        return mReminderModeList;
    }

    public void insert(ReminderMode reminderMode) {
        new insertAsyncTask(mReminderModeDao).execute(reminderMode);
    }

    private static class insertAsyncTask extends AsyncTask<ReminderMode, Void, Void> {
        private ReminderModeDao mAsyncTaskDao;

        insertAsyncTask(ReminderModeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ReminderMode... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}