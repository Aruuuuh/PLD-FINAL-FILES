package com.insightout.medicare.broadcastReceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.insightout.medicare.R;
import com.insightout.medicare.activity.AlarmActivity;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    String title, desc, date, time;

    @Override
    public void onReceive(Context context, Intent intent) {

        title = intent.getStringExtra("TITLE");
        desc = intent.getStringExtra("DESC");
        date = intent.getStringExtra("DATE");
        time = intent.getStringExtra("TIME");

        // Check if the Android version is Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel for Oreo and higher
            NotificationChannel channel = new NotificationChannel("123", "MyChannel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Create an Intent for the AlarmActivity
        Intent alarmIntent = new Intent(context, AlarmActivity.class);
        alarmIntent.putExtra("TITLE", title);
        alarmIntent.putExtra("DESC", desc);
        alarmIntent.putExtra("DATE", date);
        alarmIntent.putExtra("TIME", time);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Create a PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent) // Set the PendingIntent
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true); // Automatically remove the notification when the user taps it

        // Show the notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, notificationBuilder.build());
    }
}




