package com.example.androidexpert5.notification;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.androidexpert5.BuildConfig;
import com.example.androidexpert5.MainActivity;
import com.example.androidexpert5.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotificationReceiver extends BroadcastReceiver {
    private final int NOTIFICATION_DAILY_ID = 1;
    private final int NOTIFICATION_RELEASE_ID = 2;
    private final String NOTIFICATION_ID = "notification_id";
    private final String CHANNEL_ID = "channel_01";
    private final CharSequence CHANNEL_NAME = "my channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        int flag = intent.getIntExtra(NOTIFICATION_ID, 0);

        if (flag == NOTIFICATION_DAILY_ID) {
            dailyReminder(context);
        } else if (flag == NOTIFICATION_RELEASE_ID) {
            releaseReminder(context);
        }
    }

    public void startAlarmDaily(Context context, Boolean isNotification) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent;

        if (isNotification) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Calendar currTime = Calendar.getInstance();

            if (currTime.after(calendar)) {
                calendar.add(Calendar.DATE, 1);
            }

            intent.putExtra(NOTIFICATION_ID, NOTIFICATION_DAILY_ID);

            pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_DAILY_ID, intent, PendingIntent.FLAG_ONE_SHOT);
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        } else {
            pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_DAILY_ID, intent, PendingIntent.FLAG_ONE_SHOT);
            pendingIntent.cancel();
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
            }
        }

    }

    public void startAlarmRelease(Context context, Boolean isNotification) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent;

        if (isNotification) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Calendar currTime = Calendar.getInstance();

            if (currTime.after(calendar)) {
                calendar.add(Calendar.DATE, 1);
            }

            intent.putExtra(NOTIFICATION_ID, NOTIFICATION_RELEASE_ID);

            pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_RELEASE_ID, intent, PendingIntent.FLAG_ONE_SHOT);
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        } else {
            pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_RELEASE_ID, intent, PendingIntent.FLAG_ONE_SHOT);
            pendingIntent.cancel();
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
            }
        }
    }

    private void dailyReminder(Context context) {
        long when = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_24dp)
                .setContentTitle(context.getResources().getString(R.string.content_daily_title))
                .setContentText(context.getResources().getString(R.string.content_text))
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_DAILY_ID, builder.build());
        }
    }

    private void releaseReminder(Context context) {
        long when = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Date currDate = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(currDate);

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.API_KEY + "&primary_release_date.gte=" + today + "&primary_release_date.lte=" + today;

        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("results");

                            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                                    .setBigContentTitle("New Movie Release");

                            for (int i = 0; i < list.length(); i++) {
                                JSONObject newMovie = list.getJSONObject(i);
                                inboxStyle.addLine(newMovie.getString("title") + " has been release today!");
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setSmallIcon(R.drawable.ic_notifications_24dp)
                                    .setContentTitle(context.getResources().getString(R.string.content_release_title))
                                    .setContentText(context.getResources().getString(R.string.content_text))
                                    .setStyle(inboxStyle)
                                    .setWhen(when)
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                                builder.setChannelId(CHANNEL_ID);

                                if (notificationManager != null) {
                                    notificationManager.createNotificationChannel(channel);
                                }
                            }

                            if (notificationManager != null) {
                                notificationManager.notify(NOTIFICATION_RELEASE_ID, builder.build());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("onError", anError + "");
                    }
                });
    }
}
