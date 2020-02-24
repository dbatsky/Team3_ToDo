package com.example.team3_todo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class Alarm extends BroadcastReceiver
{
    private AlarmManager am;
    private PendingIntent pi;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "todo::wake");
        wl.acquire();

        // Put here YOUR code.
        Bundle extras = intent.getExtras();
        ArrayList<String> titles = extras.getStringArrayList("titles");
        ArrayList<String> descs = extras.getStringArrayList("descs");
        ArrayList<String> ids = extras.getStringArrayList("ids");

        for(int i = 0; i < titles.size(); i++){
            buildNotification(context, titles.get(i), descs.get(i), Integer.parseInt(ids.get(i)));
        }
        wl.release();
    }

    public void setAlarm(Context context, ArrayList<String> titles,  ArrayList<String> descs,  ArrayList<String> ids)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // set time when the notifications are sent to the user
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);

        am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        i.putStringArrayListExtra("titles", titles);
        i.putStringArrayListExtra("descs", descs);
        i.putStringArrayListExtra("ids", ids);
        pi = PendingIntent.getBroadcast(context, 0, i, FLAG_UPDATE_CURRENT);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi); // fire every 9am morning
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void buildNotification(Context context, String title, String desc, Integer id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "C1")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(desc))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(id, builder.build());
    }
}