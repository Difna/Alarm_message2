package com.example.alarm_message;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    DatePicker datePicker;
    TimePicker timePicker;
    Button buttonSetAlarm;
    TextView info;
    final static int RQS_1 = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView)findViewById(R.id.info);
        datePicker =(DatePicker)findViewById(R.id.datePicker);
        timePicker =(TimePicker)findViewById(R.id.timePicker);

        Calendar now = Calendar.getInstance();

        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
       timePicker.setCurrentMinute(now.get(Calendar.MINUTE));

        buttonSetAlarm = (Button)findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        00);
                if (cal.compareTo(current)<=0){
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                }else {
                    setAlarm(cal);

                }

            }
        });
    }
    private void setAlarm(Calendar targetCal){
        info.setText("\n\n***\n"+"Alarm is set@"+targetCal.getTime()+"\n"+"***\n");
        Intent intent = new Intent(getBaseContext(),AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
}
