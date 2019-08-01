package com.example.alarm_message;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;


import android.telephony.SmsManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.mitwa);
        mp.start();
        Toast.makeText(context, "Alarm Received", Toast.LENGTH_LONG).show();
        String no = "9961615850";
        String msg = "blah";

        //Getting intent and PendingIntent instance
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi, null);

        Toast.makeText(context, "sucess",
                Toast.LENGTH_LONG).show();
    }
}


