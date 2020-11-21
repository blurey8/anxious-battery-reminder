package id.ac.ui.cs.mobileprogramming.reyhan.data.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;

@Dao
public interface ReminderModeDao {
    @Insert
    void insert(ReminderMode reminderMode);

    @Insert
    void insertAll(ReminderMode... reminderMode);

    @Update
    void update(ReminderMode reminderMode);

    @Delete
    void delete(ReminderMode reminderMode);

    @Query("SELECT * FROM REMINDER_MODE WHERE id = :id")
    ReminderMode getById(int id);

    @Query("SELECT * FROM REMINDER_MODE")
    LiveData<List<ReminderMode>> getAll();
}