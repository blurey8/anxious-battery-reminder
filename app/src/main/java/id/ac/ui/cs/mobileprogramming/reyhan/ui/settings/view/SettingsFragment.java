package id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.reyhan.R;
import id.ac.ui.cs.mobileprogramming.reyhan.data.model.ReminderMode;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.adapter.ReminderModeListAdapter;
import id.ac.ui.cs.mobileprogramming.reyhan.ui.settings.viewmodel.ReminderModeViewModel;

public class SettingsFragment extends Fragment {

    private ReminderModeViewModel mVeminderModeViewModel;

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    //    TODO: BELUM SELESAI

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        mVeminderModeViewModel = ViewModelProviders.of(this).get(ReminderModeViewModel.class);

        RecyclerView recyclerView = rootView.findViewById(R.id.reminder_mode_recyclerview);
        Context context = getContext();
//        Context context = getActivity();
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ReminderModeListAdapter adapter = new ReminderModeListAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mVeminderModeViewModel.getAll().observe(this, new Observer<List<ReminderMode>>() {
            @Override
            public void onChanged(@Nullable final List<ReminderMode> reminderModes) {
                // Update the cached copy of the reminderModes in the adapter.
                adapter.setReminderModes(reminderModes);
            }
        });

        return rootView;
    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
}