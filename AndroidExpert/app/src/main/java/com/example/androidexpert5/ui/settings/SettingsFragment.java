package com.example.androidexpert5.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidexpert5.R;
import com.example.androidexpert5.notification.NotificationReceiver;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private final NotificationReceiver notificationReceiver = new NotificationReceiver();
    @BindView(R.id.btn_change_language)
    Button btnChangeLanguage;
    @BindView(R.id.switch_daily_reminder)
    Switch switchDailyReminder;
    @BindView(R.id.switch_release_reminder)
    Switch switchReleaseReminder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        btnChangeLanguage.setOnClickListener(this);
        switchDailyReminder.setOnClickListener(this);
        switchReleaseReminder.setOnClickListener(this);

        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        switchDailyReminder.setChecked(sharedPreferences.getBoolean("dailySwitch", true));
        switchReleaseReminder.setChecked(sharedPreferences.getBoolean("releaseSwitch", true));

        switchDailyReminder.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                notificationReceiver.startAlarmDaily(getActivity(), true);
                editor.putBoolean("dailySwitch", true);
            } else {
                notificationReceiver.startAlarmDaily(getActivity(), false);
                editor.putBoolean("dailySwitch", false);
            }
            editor.apply();
        });

        switchReleaseReminder.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                notificationReceiver.startAlarmRelease(getActivity(), true);
                editor.putBoolean("releaseSwitch", true);
            } else {
                notificationReceiver.startAlarmRelease(getActivity(), false);
                editor.putBoolean("releaseSwitch", false);
            }
            editor.apply();
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_language) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
    }
}
