package com.example.androidexpert5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.androidexpert5.database.DatabaseFavorite;
import com.example.androidexpert5.notification.NotificationReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static DatabaseFavorite databaseFavorite;
    private final NotificationReceiver notificationReceiver = new NotificationReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);

        databaseFavorite = Room.databaseBuilder(getApplicationContext(), DatabaseFavorite.class, "fav_db").allowMainThreadQueries().build();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        boolean isDailyNotification = sharedPreferences.getBoolean("dailySwitch", true);
        boolean isReleaseNotification = sharedPreferences.getBoolean("releaseSwitch", true);

        if (isDailyNotification) {
            notificationReceiver.startAlarmDaily(getApplicationContext(), true);
        } else {
            notificationReceiver.startAlarmDaily(getApplicationContext(), false);
        }

        if (isReleaseNotification) {
            notificationReceiver.startAlarmRelease(getApplicationContext(), true);
        } else {
            notificationReceiver.startAlarmRelease(getApplicationContext(), false);
        }
    }
}
