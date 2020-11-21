package id.ac.ui.cs.mobileprogramming.reyhan.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminder_mode")
public class ReminderMode {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int range;

    public ReminderMode(int id, String name, int range) {
        this.id = id;
        this.name = name;
        this.range = range;
    }

    @Ignore
    public ReminderMode(String name, int range) {
        this.name = name;
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public static ReminderMode[] getPopulatedData() {
        return new ReminderMode[]{
                new ReminderMode("Relax Mode", 10),
                new ReminderMode("Panic Mode", 1),
        };
    }
}
