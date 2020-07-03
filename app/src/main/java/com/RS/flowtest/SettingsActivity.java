package com.RS.flowtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.RS.flowtest.DataBase.DB_Entity;
import com.RS.flowtest.Notifications.NotificationsActivity;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class SettingsActivity extends AppCompatActivity {

    TimePicker time_min = null;
    TimePicker time_max = null;
    TextView select_start = null;
    EditText nb_samples = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);


        //initialise des views
        Button button_start_notifications = (Button)findViewById(R.id.bouton_start_notifications);
        button_start_notifications.setOnClickListener(startNotificationsListener);
        //select_start = (TextView)findViewById(R.id.select_start);
        //select_start.setText(getString(R.string.select_time,7,50));

        time_min=(TimePicker)findViewById(R.id.edit_start_day);
        time_max=(TimePicker) findViewById(R.id.edit_end_day);
        nb_samples=(EditText)findViewById(R.id.edit_nb_samples);


        // get saved preferences if any
        SharedPreferences sharedPreferences = getSharedPreferences("FlowTest",MODE_PRIVATE);
        int hour_max = sharedPreferences.getInt("current_hour_max",23);
        int minute_max = sharedPreferences.getInt("current_minute_max",0);
        int hour_min = sharedPreferences.getInt("current_hour_min",7);
        int minute_min = sharedPreferences.getInt("current_minute_min",0);

        time_min.setHour(hour_min);
        time_min.setMinute(minute_min);
        time_max.setHour(hour_max);
        time_max.setMinute(minute_max);


        if (MainActivity.DEV_MODE)
        {
            CheckBox useGPS = findViewById(R.id.use_gps);
            useGPS.setVisibility(View.VISIBLE);
        }


    }

    private View.OnClickListener startNotificationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int hour_min = time_min.getHour();
            int minute_min = time_min.getMinute();
            int hour_max = time_max.getHour();
            int minute_max = time_max.getMinute();

            // Get max survey id registered in DB
            int max_survey_id = 0;
            List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamples();
            for(DB_Entity spl : samples)
            {
                int survey_id = spl.getSurvey_num();
                if (survey_id > max_survey_id)
                    max_survey_id = survey_id;
            }


            SharedPreferences sharedPreferences = getSharedPreferences("FlowTest",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("current_hour_min", hour_min);
            editor.putInt("current_minute_min", minute_min);
            editor.putInt("current_hour_max", hour_max);
            editor.putInt("current_minute_max", minute_max);

            editor.putInt("current_nb_samples", Integer.parseInt(nb_samples.getText().toString()));
            editor.putInt("current_survey_id", max_survey_id+1);
            editor.putInt("next_sample_id", 1);
            editor.apply();


            if(MainActivity.DEV_MODE) {
                String temp = "Experience started: \nStart = " + hour_min + ":" + minute_min + "\nEnd = " + hour_max + ":" + minute_max + "\nNb-samples = " + nb_samples.getText();
                Log.d("Flow_Valeur", temp);

                int duration_off = ((24 - hour_max - 1) * 60 + (60 - minute_max) + hour_min * 60 + minute_min) * 60 * 1000;
                Log.d("Flow_Valeur", "duration off = " + duration_off);
            }

            NotificationsActivity.setNextAlarm(SettingsActivity.this);

            /*
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);;
            */

            Toast.makeText(SettingsActivity.this,SettingsActivity.this.getString(R.string.toastStart) ,LENGTH_LONG).show();
            finish();

            //refresh Main screen
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    };







}
