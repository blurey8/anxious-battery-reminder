package id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;

public class ReminderModeListAdapter extends RecyclerView.Adapter<ReminderModeListAdapter.ReminderModeViewHolder> {
    private final LayoutInflater mInflater;
    private List<ReminderMode> mReminderModes; // Cached copy of words
    private Context mContext;

    public ReminderModeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public ReminderModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.settings_reminder_mode_item, parent, false);
        return new ReminderModeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderModeViewHolder holder, int position) {
        Log.d("POSITION", String.valueOf(position));
        if (mReminderModes != null) {
            ReminderMode current = mReminderModes.get(position);
            holder.modeNameItemView.setText(current.getName());
            holder.modeRangeItemView.setText(
                    String.format(mContext.getString(R.string.mode_range_text),
                            String.valueOf(current.getRange())));
        } else {
            // Covers the case of data not being ready yet.
            holder.modeNameItemView.setText(mContext.getText(R.string.mode_name_text));
            holder.modeRangeItemView.setText(mContext.getText(R.string.mode_range_text));
        }
    }


    public void setReminderModes(List<ReminderMode> reminderModes) {
        mReminderModes = reminderModes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mReminderModes != null)
            return mReminderModes.size();
        else return 0;
    }

    class ReminderModeViewHolder extends RecyclerView.ViewHolder {
        private final TextView modeNameItemView;
        private final TextView modeRangeItemView;

        private ReminderModeViewHolder(View itemView) {
            super(itemView);
            modeNameItemView = itemView.findViewById(R.id.mode_name);
            modeRangeItemView = itemView.findViewById(R.id.mode_range);
        }
    }
}
