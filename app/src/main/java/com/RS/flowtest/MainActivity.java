package com.RS.flowtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.RS.flowtest.DataBase.DB_Entity;
import com.RS.flowtest.DataBase.SampleDatabase;
import com.RS.flowtest.Notifications.MyReceiver;
import com.RS.flowtest.Notifications.NotificationsActivity;
import com.RS.flowtest.Results.ResultsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean DEV_MODE = true;

    public static SampleDatabase sampleDatabase;

    TextView samples_counter ;
    Button button_start ;
    Button button_stop ;
    Button button_results ;
    Button button_add ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sampleDatabase = Room.databaseBuilder(getApplicationContext(), SampleDatabase.class,"sampledb").allowMainThreadQueries().build();
        sampleDatabase = SampleDatabase.getInstance(getApplicationContext());

        NotificationsActivity.createNotificationChannel(this);

        //initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialise les boutons et textview
        samples_counter = (TextView) findViewById(R.id.sample_counter) ;
        button_start = (Button)findViewById(R.id.bouton_start);
        button_start.setOnClickListener(startlistener);
        button_stop = (Button)findViewById(R.id.bouton_stop);
        button_stop.setOnClickListener(stoplistener);
        button_results = (Button)findViewById(R.id.bouton_results);
        button_results.setOnClickListener(resultslistener);
        button_add = (Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(addListener);

        //check if survey ongoing
        SharedPreferences sharedPreferences = getSharedPreferences("FlowTest", MODE_PRIVATE);
        int current_survey_id = sharedPreferences.getInt("current_survey_id", 0);
        int current_nb_samples = sharedPreferences.getInt("current_nb_samples", 0);
        int nb_sample_done = sharedPreferences.getInt("next_sample_id", 1)-1;
        boolean results_available = sharedPreferences.getBoolean("results_available", false);
        if (results_available == false)
            button_results.setVisibility(View.GONE);
        else
            button_results.setVisibility(View.VISIBLE);

        //adapt Main screen to current phase
        displayPhase(current_survey_id, nb_sample_done, current_nb_samples);

        if(DEV_MODE) {
            // bouton de test dev
            Button button_debug = (Button) findViewById(R.id.bouton_debug);
            button_debug.setOnClickListener(debuglistener);
            Button delete_DB = findViewById(R.id.bouton_deletedb);
            delete_DB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 1; i == 4; i++) {
                        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesFromSurveyId(i);

                        for (DB_Entity spl : samples) {
                            sampleDatabase.myDao().deleteSample(spl);
                        }
                    }
                }
            });

            // bouton de test export csv
            Button button_csv = (Button) findViewById(R.id.button_csv);
            button_csv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    export();
                }
            });

            //checking if alarm is working with pendingIntent
            Intent intent = new Intent(this, MyReceiver.class);//the same as up
            //intent.setAction(MyReceiver.ACTION_ALARM_RECEIVER);//the same as up
            boolean isWorking = (PendingIntent.getBroadcast(this, NotificationsActivity.REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE) != null);//just changed the flag
            if(DEV_MODE)
                Log.d("Flow_etape", "alarm is " + (isWorking ? "" : "not") + " working...");

            //affichage de l'heure de la prochaine alarme
            TextView next_alarm = findViewById(R.id.next_alarm);
            long next_alarm_time = sharedPreferences.getLong("next_alarm_time", 0);
            if (next_alarm_time != 0)
                next_alarm.setText(new SimpleDateFormat("MMM dd, HH:mm").format(next_alarm_time));


            // afficher boutons et affichages de dev
            next_alarm.setVisibility(View.VISIBLE);
            button_csv.setVisibility(View.VISIBLE);
            button_debug.setVisibility(View.VISIBLE);
            delete_DB.setVisibility(View.VISIBLE);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("FlowTest", MODE_PRIVATE);
        boolean main_screen_refresh = sharedPreferences.getBoolean("main_screen_refresh", false);

        if (main_screen_refresh == true) {
            recreate();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("main_screen_refresh", false);
            editor.apply();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent intent = new Intent(getApplicationContext(), InfoPopActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private View.OnClickListener startlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);;
        }
    };

    private View.OnClickListener stoplistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setMessage(getString(R.string.stop_confirmation))
                    .setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                            SharedPreferences sharedPreferences = getSharedPreferences("FlowTest",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //initialize next sample id AND current survey id to 0
                            editor.putInt("next_sample_id", 0);
                            editor.putInt("current_survey_id", 0);
                            editor.apply();

                            //cancel next alarm
                            NotificationsActivity.cancelNextAlarm(MainActivity.this);

                            //refresh main screen to phase 0
                            displayPhase(0,0,0);
                        }
                    })
                    .setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            //nothing
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
    };


    private View.OnClickListener resultslistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
            startActivity(intent);;

        }
    };

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), SampleActivity.class);
            //intent.putExtra("from_main",true);
            startActivity(intent);;

        }
    };

    private View.OnClickListener debuglistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //NotificationsActivity.setNextAlarm(MainActivity.this);

            //NotificationsActivity.CreateNotification(MainActivity.this);

            //Intent intent = new Intent(getApplicationContext(), SampleActivity.class);
            //startActivity(intent);;



            /*
            //fill in total_score
            List<DB_Entity> samples =  MainActivity.sampleDatabase.myDao().getSamples();
            for(DB_Entity spl : samples)
            {
                int total_score = spl.getSample_56_enjoy()
                        + spl.getSample_57_interesting()
                        + spl.getSample_58_concentrating()
                        + spl.getSample_59_expectations()
                        + spl.getSample_60_control()
                        + spl.getSample_61_involved()
                        + spl.getSample_62_deal()
                        + spl.getSample_63_important()
                        + spl.getSample_64_others_expectations()
                        + spl.getSample_65_succeeding()
                        - spl.getSample_66_something_else()
                        + spl.getSample_67_feel_good();
                spl.setTotal_score(total_score);
                sampleDatabase.myDao().updateUsers(spl);
            }
            Log.d("Flow_etape","total_score rentré!");
            */


            //export();
        }
    };

    public void export(){

        String LOG_TAG = "Flow_Export";

        // generate data

        StringBuilder data = new StringBuilder();
        data.append("id;survey_num;sample_num;sample_date;latitude;longitude;sample_02_where;sample_03_what;sample_04_what_else;sample_05_mind;sample_12_alone_yes;sample_15_spouse; sample_16_boss;sample_17_coworker;sample_18_friends;sample_19_girl_boyfriend;sample_20_mother;sample_21_father;sample_22_teacher;sample_23_classmate;sample_24_other;sample_25_children;sample_26_children_who;sample_27_siblings;sample_28_siblings_who;sample_43_wanted;sample_44_had;sample_45_nothing_else;sample_56_enjoy;sample_57_interesting;sample_58_concentrating;sample_59_expectations;sample_60_control;sample_61_involved;sample_62_deal;sample_63_important;sample_64_others_expectations;sample_65_succeeding;sample_66_something_else;sample_67_feel_good;total_score");

        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamples();
        //List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesByScore(4);


        String data_line ="";

        for(DB_Entity spl : samples)
        {
            // get DB values
            int id = spl.getId();
            int survey_num = spl.getSurvey_num();
            int sample_num = spl.getSample_num();
            Long sample_date = spl.getSample_date();
            Double latitude = spl.getLatitude();
            Double longitude = spl.getLongitude();
            String sample_02_where = spl.getSample_02_where();
            String sample_03_what = spl.getSample_03_what();
            String sample_04_what_else = spl.getSample_04_what_else();
            String sample_05_mind = spl.getSample_05_mind();
            boolean sample_12_alone_yes = spl.isSample_12_alone_yes();
            boolean sample_15_spouse = spl.isSample_15_spouse();
            boolean sample_16_boss = spl.isSample_16_boss();
            boolean sample_17_coworker = spl.isSample_17_coworker();
            boolean sample_18_friends = spl.isSample_18_friends();
            boolean sample_19_girl_boyfriend = spl.isSample_19_girl_boyfriend();
            boolean sample_20_mother = spl.isSample_20_mother();
            boolean sample_21_father = spl.isSample_21_father();
            boolean sample_22_teacher = spl.isSample_22_teacher();
            boolean sample_23_classmate = spl.isSample_23_classmate();
            boolean sample_24_other = spl.isSample_24_other();
            boolean sample_25_children = spl.isSample_25_children();
            String sample_26_children_who = spl.getSample_26_children_who();
            boolean sample_27_siblings = spl.isSample_27_siblings();
            String sample_28_siblings_who = spl.getSample_28_siblings_who();
            boolean sample_43_wanted = spl.isSample_43_wanted();
            boolean sample_44_had = spl.isSample_44_had();
            boolean sample_45_nothing_else = spl.isSample_45_nothing_else();
            int sample_56_enjoy =spl.getSample_56_enjoy();
            int sample_57_interesting = spl.getSample_57_interesting();
            int sample_58_concentrating = spl.getSample_58_concentrating();
            int sample_59_expectations = spl.getSample_59_expectations();
            int sample_60_control = spl.getSample_60_control();
            int sample_61_involved = spl.getSample_61_involved();
            int sample_62_deal = spl.getSample_62_deal();
            int sample_63_important = spl.getSample_63_important();
            int sample_64_others_expectations = spl.getSample_64_others_expectations();
            int sample_65_succeeding = spl.getSample_65_succeeding();
            int sample_66_something_else = spl.getSample_66_something_else();
            int sample_67_feel_good= spl.getSample_67_feel_good();
            int total_score = spl.getTotal_score();

            String dateString = new SimpleDateFormat("EEE MMM dd, yyyy HH:mm").format(sample_date);
            data_line = "\n"+ id+";"+survey_num+";"+sample_num+";"+dateString+";"+latitude+";"+longitude+";"+sample_02_where+";"+sample_03_what+";"+sample_04_what_else+";"+sample_05_mind+";"+sample_12_alone_yes+";"+sample_15_spouse+";"+sample_16_boss+";"+sample_17_coworker+";"+sample_18_friends+";"+sample_19_girl_boyfriend+";"+sample_20_mother+";"+sample_21_father+";"+sample_22_teacher+";"+sample_23_classmate+";"+sample_24_other+";"+sample_25_children+";"+sample_26_children_who+";"+sample_27_siblings+";"+sample_28_siblings_who+";"+sample_43_wanted+";"+sample_44_had+";"+sample_45_nothing_else+";"+sample_56_enjoy+";"+sample_57_interesting+";"+sample_58_concentrating+";"+sample_59_expectations+";"+sample_60_control+";"+sample_61_involved+";"+sample_62_deal+";"+sample_63_important+";"+sample_64_others_expectations+";"+sample_65_succeeding+";"+sample_66_something_else+";"+sample_67_feel_good+";"+total_score+";";
            data.append(data_line);
        }

        try{
            String today = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());

            //saving the file into device
            FileOutputStream out = openFileOutput("Data_"+today+".csv", Context.MODE_PRIVATE);
            //FileOutputStream out = openFileOutput("data", Context.MODE_PRIVATE);
            out.write(data.toString().getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "Data_"+today+".csv");
            Uri path = FileProvider.getUriForFile(context, "com.RS.flowtest.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/rtf");
            String to[] = {"days2freedom@gmail.com"};
            fileIntent.putExtra(Intent.EXTRA_EMAIL,to);
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Flow Test - Data_"+today+".csv");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            //fileIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://" + CachedFileProvider.AUTHORITY + "/" + fileName));
            //fileIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path.toString()));

            if(DEV_MODE)
            {
                Log.d(LOG_TAG,"filelocation = " + filelocation.toString());
                Log.d(LOG_TAG,"Uri = " + path.toString());
                Log.d(LOG_TAG,"intent ready, start activity...");
            }


            startActivity(Intent.createChooser(fileIntent, "Send email"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void displayPhase(int current_survey_id, int nb_sample_done, int current_nb_samples)
    {
        if (current_survey_id == 0)    //if no survey ongoing
        {
            button_stop.setVisibility(View.GONE);
            button_add.setVisibility(View.GONE);
            button_start.setVisibility(View.VISIBLE);

            samples_counter.setText(getString(R.string.welcome));

        }else // if survey ongoing
        {
            button_stop.setVisibility(View.VISIBLE);
            button_add.setVisibility(View.VISIBLE);
            button_start.setVisibility(View.GONE);

            if(nb_sample_done==0)
                samples_counter.setText(getString(R.string.started));
            else
                samples_counter.setText(getString(R.string.counter1)+"\n"+nb_sample_done+" / "+ current_nb_samples+"\n"+getString(R.string.counter2));
        }


    }



}
