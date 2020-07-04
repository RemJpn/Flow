package com.RS.flowtest.Notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;

import com.RS.flowtest.MainActivity;
import com.RS.flowtest.R;
import com.RS.flowtest.SampleActivity;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.widget.Toast.LENGTH_LONG;

public class NotificationsActivity {

    public static final int REQUEST_CODE = 101;

    public static void setNextAlarm(Context context){

        // only set next alarm if survey ongoing (especially after reboot)
        SharedPreferences sharedPreferences = context.getSharedPreferences("FlowTest",MODE_PRIVATE);
        int current_survey_id = sharedPreferences.getInt("current_survey_id",0);
        if (current_survey_id == 0) //if no survey ongoing
            return;
        else {

            // Enable a receiver
            ComponentName receiver = new ComponentName(context, MyReceiver.class);
            PackageManager pm = context.getPackageManager();
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);


            AlarmManager alarmMgr;
            PendingIntent alarmIntent;

            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(context, MyReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Set the alarm randomly
            Random rnd = new Random();
            long interval_moyen = 15 * 7 * 60 * 60 * 1000 / 40; //7 jours, 15 heures, 60 minutes, 60 secones, 1000 millis
            long interval_min = 30 * 60 * 1000; // minimum of 30 minutes

            long interval_random = (long) (rnd.nextGaussian() * (interval_moyen - 1 * 60 * 60 * 1000) + interval_moyen); //gaussienne de moyenne "interval_moyen", et d ecart type "interval_moyen" moins 1 heure
            if(MainActivity.DEV_MODE)
                Log.d("Flow_Valeur", Long.toString(interval_random));
            if (interval_random < interval_min)
                interval_random = interval_min;

            Calendar nextAlarmCalendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            if(MainActivity.DEV_MODE)
                Log.d("Flow_Valeur", "Current date/time: " + formatter.format(nextAlarmCalendar.getTime()));

            //nextAlarmCalendar.setTimeInMillis(System.currentTimeMillis() + interval_random);
            nextAlarmCalendar.setTimeInMillis(System.currentTimeMillis() + 5*60*1000);

            String NextDate1 = formatter.format(nextAlarmCalendar.getTime());


            //Get current alarm time in order to compare to min and max
            int alarm_hour = nextAlarmCalendar.get(Calendar.HOUR_OF_DAY);
            int alarm_minute = nextAlarmCalendar.get(Calendar.MINUTE);

            // Get saved min and max time
            int hour_max = sharedPreferences.getInt("current_hour_max", 23);
            int minute_max = sharedPreferences.getInt("current_minute_max", 0);
            int hour_min = sharedPreferences.getInt("current_hour_min", 7);
            int minute_min = sharedPreferences.getInt("current_minute_min", 0);
            int duration_off = ((24 - hour_max - 1) * 60 + (60 - minute_max) + hour_min * 60 + minute_min) * 60 * 1000;

            if (MainActivity.DEV_MODE) {
                Toast.makeText(context, formatter.format(nextAlarmCalendar.getTime()), LENGTH_LONG).show();
                Log.d("Flow_Valeur", "Next alarm: " + NextDate1);
            }

            //If the alarm is set after the max limit OR before the min limit => postpone by duration between max and min (duration of "off" time)
            if (alarm_hour > hour_max || (alarm_hour == hour_max && alarm_minute > minute_max) || alarm_hour < hour_min || (alarm_hour == hour_min && alarm_minute < minute_min)) {
                nextAlarmCalendar.setTimeInMillis(nextAlarmCalendar.getTimeInMillis() + duration_off);
                String NextDate2 = formatter.format(nextAlarmCalendar.getTime());
            }


            // finally, set the next alarm
            alarmMgr.set(AlarmManager.RTC_WAKEUP, nextAlarmCalendar.getTimeInMillis(), alarmIntent);


            // for development phase purpose, save next alarm time in shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("next_alarm_time", nextAlarmCalendar.getTimeInMillis());
            editor.apply();

        }
    }

    public static void cancelNextAlarm(Context context){

        // Disable a receiver
        ComponentName receiver = new ComponentName(context, MyReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.cancel(alarmIntent);

        // for development phase purpose, save next alarm time in shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("FlowTest",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("next_alarm_time", 0);
        editor.apply();

    }



    public static void CreateNotification (Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("FlowTest", MODE_PRIVATE);
        int next_sample_id = sharedPreferences.getInt("next_sample_id",0);
        int planned_nb_sample = sharedPreferences.getInt("current_nb_samples",0);

        String channel_id = context.getString(R.string.channel_id);
        String notifTitre= context.getString(R.string.notifTitre);
        String notifText= context.getString(R.string.notifText1)+" "+Integer.toString(next_sample_id) + "/" + (planned_nb_sample);

        if(MainActivity.DEV_MODE)
        {
            //for test purpose only, add the current minutes to the notification
            Calendar calendrier = Calendar.getInstance();
            int hour = calendrier.get(Calendar.HOUR_OF_DAY);
            int minute = calendrier.get(Calendar.MINUTE);
            notifText= Integer.toString(hour)+":"+Integer.toString(minute) + " "+notifText;
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_stat_call_white)
                .setContentTitle(notifTitre)
                .setContentText(notifText)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(notifText))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intent = new Intent(context, SampleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1001,intent,0);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());

        // in case the user does not click on the notification,
        // already set the next alarm (will be overwritten by another new alarm if/when user complete the sample
        NotificationsActivity.setNextAlarm(context);

    }


    public static void createNotificationChannel(Context context) {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channel_id = context.getString(R.string.channel_id);
            CharSequence name = context.getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, name, importance);
            //channel.setDescription(description);

            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{500,500,500,500,500});
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
