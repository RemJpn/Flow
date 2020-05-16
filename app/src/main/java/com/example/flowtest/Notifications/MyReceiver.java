package com.example.flowtest.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d("Receiver", "onReceive: BOOT_COMPLETED");
                NotificationsActivity.setNextAlarm(context);

                return;
            }
        }

        NotificationsActivity.CreateNotification(context);


    }
}
