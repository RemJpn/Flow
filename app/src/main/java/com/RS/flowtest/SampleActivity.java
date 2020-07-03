package com.RS.flowtest;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import com.RS.flowtest.DataBase.DB_Entity;
import com.RS.flowtest.DataBase.SampleDatabase;
import com.RS.flowtest.Notifications.NotificationsActivity;
import com.RS.flowtest.Results.ResultsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    public static SampleDatabase sampleDatabase;


    //Layouts
    LinearLayout sample_layout_part1 = null;
    LinearLayout sample_layout_part2 = null;
    LinearLayout sample_layout_part2_1 = null;
    LinearLayout sample_layout_part3 = null;
    LinearLayout sample_layout_part4 = null;
    LinearLayout sample_layout_part5 = null;
    LinearLayout sample_layout_part6 = null;
    LinearLayout sample_layout_part7 = null;
    LinearLayout sample_layout_part8 = null;
    LinearLayout sample_layout_part9 = null;
    LinearLayout sample_layout_part10 = null;
    LinearLayout sample_layout_part11 = null;
    LinearLayout sample_layout_part12 = null;
    LinearLayout sample_layout_part13 = null;
    LinearLayout sample_layout_part14 = null;
    LinearLayout sample_layout_part15 = null;
    TextView error_message = null;
    int current_page;

    // declaration of all widgets

    //Buttons
    Button next_button;
    Button previous_button;
    Button done_button;

    // 1st part
    EditText widget_02_where= null;
    EditText widget_03_what= null;
    EditText widget_04_what_else= null;
    EditText widget_05_mind= null;

    // 2nd part
    RadioGroup alone_radio = null;
    CheckBox widget_15_spouse= null;
    CheckBox widget_16_boss= null;
    CheckBox widget_17_coworker= null;
    CheckBox widget_18_friends= null;
    CheckBox widget_19_girl_boyfriend= null;
    CheckBox widget_20_mother= null;
    CheckBox widget_21_father= null;
    CheckBox widget_22_teacher= null;
    CheckBox widget_23_classmate= null;
    CheckBox widget_24_other= null;
    CheckBox widget_25_children= null;
    EditText widget_26_children_who= null;
    CheckBox widget_27_siblings= null;
    EditText widget_28_siblings_who= null;

    // 3rd part
    RadioGroup why_radio = null;

    // 4th part
    // Not at all = 1 ; a little = 2 ; Somewhat = 3; Very much = 4
    RadioGroup widget_56_enjoy = null;
    RadioGroup widget_57_interesting = null;
    RadioGroup widget_58_concentrating = null;
    RadioGroup widget_59_expectations = null;
    RadioGroup widget_60_control = null;
    RadioGroup widget_61_involved = null;
    RadioGroup widget_62_deal = null;
    RadioGroup widget_63_important = null;
    RadioGroup widget_64_others_expectations = null;
    RadioGroup widget_65_succeeding = null;
    RadioGroup widget_66_something_else = null;
    RadioGroup widget_67_feel_good = null;

    // declaration of sample variables
    int id;
    int survey_num;
    int sample_num;
    Long sample_date;
    Double latitude = null;
    Double longitude = null;
    String sample_02_where = null;
    String sample_03_what = null;
    String sample_04_what_else = null;
    String sample_05_mind = null;
    boolean sample_12_alone_yes = false;
    boolean sample_15_spouse = false;
    boolean sample_16_boss = false;
    boolean sample_17_coworker = false;
    boolean sample_18_friends = false;
    boolean sample_19_girl_boyfriend = false;
    boolean sample_20_mother = false;
    boolean sample_21_father = false;
    boolean sample_22_teacher = false;
    boolean sample_23_classmate = false;
    boolean sample_24_other = false;
    boolean sample_25_children = false;
    String sample_26_children_who = null;
    boolean sample_27_siblings = false;
    String sample_28_siblings_who = null;
    boolean sample_43_wanted = false;
    boolean sample_44_had = false;
    boolean sample_45_nothing_else = false;
    int sample_56_enjoy;
    int sample_57_interesting;
    int sample_58_concentrating;
    int sample_59_expectations;
    int sample_60_control;
    int sample_61_involved;
    int sample_62_deal;
    int sample_63_important;
    int sample_64_others_expectations;
    int sample_65_succeeding;
    int sample_66_something_else;
    int sample_67_feel_good;

    // feeling (part4) weights to be adjusted if necessary
    static int NOT_AT_ALL = 0;
    static int LITTLE = 1;
    static int SOMEWHAT = 2;
    static int VERY_MUCH =3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_screen);

        current_page = 1;

        //Hide the keyboard that otherwise opens automatically
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initialize part X layouts
        sample_layout_part1 = findViewById(R.id.sample_layout_part1);
        sample_layout_part2 = findViewById(R.id.sample_layout_part2);
        sample_layout_part2_1 = findViewById(R.id.sample_layout_part2_1);
        sample_layout_part3 = findViewById(R.id.sample_layout_part3);
        sample_layout_part4 = findViewById(R.id.sample_layout_part4);
        sample_layout_part5 = findViewById(R.id.sample_layout_part5);
        sample_layout_part6 = findViewById(R.id.sample_layout_part6);
        sample_layout_part7 = findViewById(R.id.sample_layout_part7);
        sample_layout_part8 = findViewById(R.id.sample_layout_part8);
        sample_layout_part9 = findViewById(R.id.sample_layout_part9);
        sample_layout_part10 = findViewById(R.id.sample_layout_part10);
        sample_layout_part11 = findViewById(R.id.sample_layout_part11);
        sample_layout_part12 = findViewById(R.id.sample_layout_part12);
        sample_layout_part13 = findViewById(R.id.sample_layout_part13);
        sample_layout_part14 = findViewById(R.id.sample_layout_part14);
        sample_layout_part15 = findViewById(R.id.sample_layout_part15);

        //set part x layout height to screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        sample_layout_part1.getLayoutParams().height = height;
        sample_layout_part2.getLayoutParams().height = height;
        sample_layout_part3.getLayoutParams().height = height;
        sample_layout_part4.getLayoutParams().height = height;
        sample_layout_part5.getLayoutParams().height = height;
        sample_layout_part6.getLayoutParams().height = height;
        sample_layout_part7.getLayoutParams().height = height;
        sample_layout_part8.getLayoutParams().height = height;
        sample_layout_part9.getLayoutParams().height = height;
        sample_layout_part10.getLayoutParams().height = height;
        sample_layout_part11.getLayoutParams().height = height;
        sample_layout_part12.getLayoutParams().height = height;
        sample_layout_part13.getLayoutParams().height = height;
        sample_layout_part14.getLayoutParams().height = height;
        sample_layout_part15.getLayoutParams().height = height;

        //Hide Parts 2/3/4 at first
        sample_layout_part1.setVisibility(View.VISIBLE);
        sample_layout_part2.setVisibility(View.GONE);
        sample_layout_part2_1.setVisibility(View.GONE);
        sample_layout_part3.setVisibility(View.GONE);
        sample_layout_part4.setVisibility(View.GONE);
        sample_layout_part5.setVisibility(View.GONE);
        sample_layout_part6.setVisibility(View.GONE);
        sample_layout_part7.setVisibility(View.GONE);
        sample_layout_part8.setVisibility(View.GONE);
        sample_layout_part9.setVisibility(View.GONE);
        sample_layout_part10.setVisibility(View.GONE);
        sample_layout_part11.setVisibility(View.GONE);
        sample_layout_part12.setVisibility(View.GONE);
        sample_layout_part13.setVisibility(View.GONE);
        sample_layout_part14.setVisibility(View.GONE);
        sample_layout_part15.setVisibility(View.GONE);

        // initialize and hide error message
        error_message = findViewById(R.id.error_data_incomplete);
        error_message.setVisibility(View.GONE);

        //initialize buttons
        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(navListener);
        next_button.setVisibility(View.GONE);
        previous_button = findViewById(R.id.previous_button);
        previous_button.setOnClickListener(navListener);
        previous_button.setVisibility(View.GONE);
        done_button = findViewById(R.id.done_button);
        done_button.setOnClickListener(navListener);
        done_button.setVisibility(View.GONE);

        //Initialize widgets
        // 1st part
        widget_02_where = findViewById(R.id.widget_02_where);
        widget_02_where.addTextChangedListener(part1_textchanged);
        widget_03_what= findViewById(R.id.widget_03_what);
        widget_03_what.addTextChangedListener(part1_textchanged);
        widget_04_what_else= findViewById(R.id.widget_04_what_else);
        widget_05_mind= findViewById(R.id.widget_05_mind);

        // 2nd part
        alone_radio = findViewById(R.id.alone_radio);
        alone_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if (checkedId == R.id.sample_13_alone_no) {
                    sample_layout_part2_1.setVisibility(View.VISIBLE);
                    next_button.setVisibility(View.VISIBLE);
                }else{
                    navListener.onClick(next_button);
                }
            }
        });
        widget_15_spouse = findViewById(R.id.widget_15_spouse);
        widget_16_boss = findViewById(R.id.widget_16_boss);
        widget_17_coworker=  findViewById(R.id.widget_17_coworker);
        widget_18_friends=  findViewById(R.id.widget_18_friends);
        widget_19_girl_boyfriend= findViewById(R.id.widget_19_girl_boyfriend);
        widget_20_mother= findViewById(R.id.widget_20_mother);
        widget_21_father= findViewById(R.id.widget_21_father);
        widget_22_teacher= findViewById(R.id.widget_22_teacher);
        widget_23_classmate= findViewById(R.id.widget_23_classmate);
        widget_24_other= findViewById(R.id.widget_24_other);
        widget_25_children= findViewById(R.id.widget_25_children);
        widget_25_children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (widget_25_children.isChecked()) {
                    widget_26_children_who.setVisibility(View.VISIBLE);
                }else{
                    widget_26_children_who.setVisibility(View.GONE);
                }
            }
        });
        widget_26_children_who= findViewById(R.id.widget_26_children_who);
        widget_26_children_who.setVisibility(View.GONE);
        widget_27_siblings= findViewById(R.id.widget_27_siblings);
        widget_27_siblings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (widget_27_siblings.isChecked()) {
                    widget_28_siblings_who.setVisibility(View.VISIBLE);
                }else{
                    widget_28_siblings_who.setVisibility(View.GONE);
                }
            }
        });
        widget_28_siblings_who= findViewById(R.id.widget_28_siblings_who);
        widget_28_siblings_who.setVisibility(View.GONE);

        // 3rd part
        why_radio = findViewById(R.id.why_radio);
        why_radio.setOnCheckedChangeListener(radioNavListener);

        // 4th part
        widget_56_enjoy = findViewById(R.id.widget_56_enjoy);
        widget_56_enjoy.setOnCheckedChangeListener(radioNavListener);
        widget_57_interesting = findViewById(R.id.widget_57_interesting);
        widget_57_interesting.setOnCheckedChangeListener(radioNavListener);
        widget_58_concentrating = findViewById(R.id.widget_58_concentrating);
        widget_58_concentrating.setOnCheckedChangeListener(radioNavListener);
        widget_59_expectations = findViewById(R.id.widget_59_expectations);
        widget_59_expectations.setOnCheckedChangeListener(radioNavListener);
        widget_60_control = findViewById(R.id.widget_60_control);
        widget_60_control.setOnCheckedChangeListener(radioNavListener);
        widget_61_involved = findViewById(R.id.widget_61_involved);
        widget_61_involved.setOnCheckedChangeListener(radioNavListener);
        widget_62_deal = findViewById(R.id.widget_62_deal);
        widget_62_deal.setOnCheckedChangeListener(radioNavListener);
        widget_63_important = findViewById(R.id.widget_63_important);
        widget_63_important.setOnCheckedChangeListener(radioNavListener);
        widget_64_others_expectations = findViewById(R.id.widget_64_others_expectations);
        widget_64_others_expectations.setOnCheckedChangeListener(radioNavListener);
        widget_65_succeeding = findViewById(R.id.widget_65_succeeding);
        widget_65_succeeding.setOnCheckedChangeListener(radioNavListener);
        widget_66_something_else = findViewById(R.id.widget_66_something_else);
        widget_66_something_else.setOnCheckedChangeListener(radioNavListener);
        widget_67_feel_good = findViewById(R.id.widget_67_feel_good);
        widget_67_feel_good.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                done_button.setVisibility(View.VISIBLE);
            }
        });

    }


    private RadioGroup.OnCheckedChangeListener radioNavListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //wait xx ms and go next
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navListener.onClick(next_button);
                }
            }, 200);
        }
    };

    private View.OnClickListener navListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(MainActivity.DEV_MODE)
                Log.d("Flow_Value", "current page = " + current_page);
            int direction;
            switch(v.getId()){
                //PREVIOUS BUTTON
                case R.id.previous_button:
                    direction = -1;
                    switch(current_page) {
                        case 2:
                            transitionAnimation(sample_layout_part2,sample_layout_part1,direction);
                            previous_button.setVisibility(View.GONE);
                            break;
                        case 3:
                            transitionAnimation(sample_layout_part3,sample_layout_part2,direction);
                            break;
                        case 4:
                            transitionAnimation(sample_layout_part4,sample_layout_part3,direction);
                            break;
                        case 5:
                            transitionAnimation(sample_layout_part5,sample_layout_part4,direction);
                            break;
                        case 6:
                            transitionAnimation(sample_layout_part6,sample_layout_part5,direction);
                            break;
                        case 7:
                            transitionAnimation(sample_layout_part7,sample_layout_part6,direction);
                            break;
                        case 8:
                            transitionAnimation(sample_layout_part8,sample_layout_part7,direction);
                            break;
                        case 9:
                            transitionAnimation(sample_layout_part9,sample_layout_part8,direction);
                            break;
                        case 10:
                            transitionAnimation(sample_layout_part10,sample_layout_part9,direction);
                            break;
                        case 11:
                            transitionAnimation(sample_layout_part11,sample_layout_part10,direction);
                            break;
                        case 12:
                            transitionAnimation(sample_layout_part12,sample_layout_part11,direction);
                            break;
                        case 13:
                            transitionAnimation(sample_layout_part13,sample_layout_part12,direction);
                            break;
                        case 14:
                            transitionAnimation(sample_layout_part14,sample_layout_part13,direction);
                            break;
                        case 15:
                            transitionAnimation(sample_layout_part15,sample_layout_part14,direction);
                            done_button.setVisibility(View.GONE);
                            break;
                    }
                    error_message.setVisibility(View.GONE);
                    next_button.setVisibility(View.VISIBLE);
                    if(current_page > 1)
                        current_page --;
                    break;

                //NEXT BUTTON
                case R.id.next_button:
                    direction = 1;
                    switch (current_page){
                        case 1:
                            //if data entered we proceed, else error
                            if(widget_02_where.getText().toString().isEmpty() || widget_03_what.getText().toString().isEmpty())
                                error_message.setVisibility(View.VISIBLE);
                            else{
                                transitionAnimation(sample_layout_part1,sample_layout_part2,direction);
                                previous_button.setVisibility(View.VISIBLE);
                                error_message.setVisibility(View.GONE);
                                current_page ++;
                                //
                                if (alone_radio.getCheckedRadioButtonId() != -1)
                                    next_button.setVisibility(View.VISIBLE);
                                else
                                    next_button.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            if(alone_radio.getCheckedRadioButtonId()==-1)
                                error_message.setVisibility(View.VISIBLE);
                            else {
                                transitionAnimation(sample_layout_part2,sample_layout_part3,direction);
                                error_message.setVisibility(View.GONE);
                                current_page ++;
                                if (alone_radio.getCheckedRadioButtonId() == R.id.sample_12_alone_yes)
                                    sample_layout_part2_1.setVisibility(View.GONE);

                                //if page 3 question already answered, display "next" button, else hide it
                                if (why_radio.getCheckedRadioButtonId() != -1)
                                    next_button.setVisibility(View.VISIBLE);
                                else
                                    next_button.setVisibility(View.GONE);
                            }
                            break;
                        case 3:
                            if(why_radio.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part3,sample_layout_part4,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 4:
                            if(widget_56_enjoy.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part4,sample_layout_part5,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 5:
                            if(widget_57_interesting.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part5,sample_layout_part6,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 6:
                            if(widget_58_concentrating.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part6,sample_layout_part7,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 7:
                            if(widget_59_expectations.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part7,sample_layout_part8,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 8:
                            if(widget_60_control.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part8,sample_layout_part9,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 9:
                            if(widget_61_involved.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part9,sample_layout_part10,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 10:
                            if(widget_62_deal.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part10,sample_layout_part11,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 11:
                            if(widget_63_important.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part11,sample_layout_part12,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 12:
                            if(widget_64_others_expectations.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part12,sample_layout_part13,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 13:
                            if(widget_65_succeeding.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                error_message.setVisibility(View.GONE);
                                transitionAnimation(sample_layout_part13,sample_layout_part14,direction);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;
                        case 14:
                            if(widget_66_something_else.getCheckedRadioButtonId() == -1)
                                error_message.setVisibility(View.VISIBLE);
                            else
                            {
                                transitionAnimation(sample_layout_part14,sample_layout_part15,direction);
                                error_message.setVisibility(View.GONE);
                                next_button.setVisibility(View.GONE);
                                current_page ++;
                            }
                            break;

                    }
                    break;
                case R.id.done_button:
                    done_button.setClickable(false);
                    // if any of the radiogroup is not selected => error
                    if(widget_56_enjoy.getCheckedRadioButtonId()==-1 ||
                            widget_57_interesting.getCheckedRadioButtonId()==-1||
                            widget_58_concentrating.getCheckedRadioButtonId()==-1||
                            widget_59_expectations.getCheckedRadioButtonId()==-1||
                            widget_60_control.getCheckedRadioButtonId()==-1||
                            widget_61_involved.getCheckedRadioButtonId()==-1||
                            widget_62_deal.getCheckedRadioButtonId()==-1||
                            widget_63_important.getCheckedRadioButtonId()==-1||
                            widget_64_others_expectations.getCheckedRadioButtonId()==-1||
                            widget_65_succeeding.getCheckedRadioButtonId()==-1||
                            widget_66_something_else.getCheckedRadioButtonId()==-1||
                            widget_67_feel_good.getCheckedRadioButtonId()==-1)
                        error_message.setVisibility(View.VISIBLE);
                    else {
                        error_message.setVisibility(View.GONE);

                        // launch save as an asynchronous task
                        SaveTask mSave = new SaveTask(SampleActivity.this);
                        mSave.execute();
                    }
                    break;
            }
            if(MainActivity.DEV_MODE)
                Log.d("Flow_Value", "new page = " + current_page);
        }
    };


    private TextWatcher part1_textchanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {        }

        @Override
        public void afterTextChanged(Editable s) {
            if (widget_02_where.length() != 0 && widget_03_what.length() != 0)
                next_button.setVisibility(View.VISIBLE);
            else
                next_button.setVisibility(View.GONE);
        }
    };

    private void transitionAnimation(final View oldView, final View newView, final int direction){

        // direction: next => 1; previous => -1
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;

        oldView.animate()
                .translationYBy(-direction*height)
                .setDuration(250)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        oldView.setVisibility(View.GONE);
                        newView.setTranslationY(direction*height);
                        newView.setVisibility(View.VISIBLE);
                        newView.animate()
                                .translationYBy(-direction*height)
                                .setDuration(250)
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {                                    }
                                    @Override
                                    public void onAnimationEnd(Animator animation) {                                    }
                                    @Override
                                    public void onAnimationCancel(Animator animation) {                                    }
                                    @Override
                                    public void onAnimationRepeat(Animator animation) {                                    }
                                });
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {

                        if(MainActivity.DEV_MODE)
                            Log.d("Flow_Etape","Oldview onAnimationCancel");
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });

    }


    //Async task to set data in the Database (Room does not accept access to DB on Thread UI)
    private class SaveTask extends AsyncTask<String,Integer,Boolean> {
        //Référence faible à l'activité
        private WeakReference<SampleActivity> mActivity = null;

        public SaveTask(SampleActivity pActivity) {
            link(pActivity);
        }

        protected void onPreExecute() {        }

        public Boolean doInBackground(String... arg0) {
            try {
                save_sample();
                Thread.sleep(1); //?????
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void onPostExecute(Boolean result){
            if(mActivity.get()!=null){
                if (result){

                    SharedPreferences sharedPreferences = getSharedPreferences("FlowTest",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int current_nb_samples = sharedPreferences.getInt("current_nb_samples",40);
                    editor.putBoolean("main_screen_refresh", true);
                    editor.putBoolean("results_available",true);

                    if(sample_num+1 > current_nb_samples) // if experience completed
                    {

                        //initialize next sample id AND current survey id to 0
                        editor.putInt("next_sample_id", 0);
                        editor.putInt("current_survey_id", 0);
                        editor.apply();

                        NotificationsActivity.cancelNextAlarm(SampleActivity.this);

                        if(MainActivity.DEV_MODE)
                            Log.d("Flow_Etape","Test completed, next alarm cancelled");

                        survey_end_dialog();

                    }else //experience not completed yet
                    {

                        Toast.makeText(mActivity.get(),getString(R.string.save_complete),Toast.LENGTH_SHORT).show();

                        //increment next sample id
                        editor.putInt("next_sample_id", sample_num +1);
                        editor.apply();

                        // set alarm for next sample (except if last sample)
                        NotificationsActivity.setNextAlarm(SampleActivity.this);

                        if(MainActivity.DEV_MODE)
                            Log.d("Flow_Etape","next alarm set");

                        if(sample_num%10 == 0)//
                            intermediate_dialog();
                        else
                            finish();

                    }

                }else
                {
                    Toast.makeText(mActivity.get(),getString(R.string.save_error),Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        protected void onProgressUpdate (Integer... prog) {
            // on Progress update
        }

        protected void onCancelled () {
            if(mActivity.get() != null)
                Toast.makeText(mActivity.get(), getString(R.string.save_cancelled), Toast.LENGTH_SHORT).show();
        }

        public void link (SampleActivity pActivity) {
            mActivity = new WeakReference<>(pActivity);
        }

        public void save_sample(){

            //get parameters
            //Part 1
            sample_02_where = widget_02_where.getText().toString();
            sample_03_what = widget_03_what.getText().toString();
            sample_04_what_else = widget_04_what_else.getText().toString();
            sample_05_mind = widget_05_mind.getText().toString();

            //Part 2
            if (alone_radio.getCheckedRadioButtonId() == R.id.sample_12_alone_yes)
                sample_12_alone_yes = true;
            else // save the following only if not alone
            {
                sample_12_alone_yes = false;
                sample_15_spouse = widget_15_spouse.isChecked();
                sample_16_boss = widget_16_boss.isChecked();
                sample_17_coworker = widget_17_coworker.isChecked();
                sample_18_friends = widget_18_friends.isChecked();
                sample_19_girl_boyfriend = widget_19_girl_boyfriend.isChecked();
                sample_20_mother = widget_20_mother.isChecked();
                sample_21_father = widget_21_father.isChecked();
                sample_22_teacher = widget_22_teacher.isChecked();
                sample_23_classmate = widget_23_classmate.isChecked();
                sample_24_other = widget_24_other.isChecked();
                sample_25_children = widget_25_children.isChecked();
                if (widget_25_children.isChecked())
                    sample_26_children_who = widget_26_children_who.getText().toString();
                sample_27_siblings = widget_27_siblings.isChecked();
                if (widget_27_siblings.isChecked())
                    sample_28_siblings_who = widget_28_siblings_who.getText().toString();
            }

            //Part 3
            switch (why_radio.getCheckedRadioButtonId()) {
                case R.id.sample_43_wanted:
                    sample_43_wanted = true;
                    sample_44_had = false;
                    sample_45_nothing_else = false;
                    break;
                case R.id.sample_44_had:
                    sample_43_wanted = false;
                    sample_44_had = true;
                    sample_45_nothing_else = false;
                    break;
                case R.id.sample_45_nothing_else:
                    sample_43_wanted = false;
                    sample_44_had = false;
                    sample_45_nothing_else = true;
                    break;
            }

            //Part 4
            // Get values from radio groups
            switch (widget_56_enjoy.getCheckedRadioButtonId()) {
                case R.id.enjoy_0: sample_56_enjoy = NOT_AT_ALL;break;
                case R.id.enjoy_1:sample_56_enjoy = LITTLE;break;
                case R.id.enjoy_2:sample_56_enjoy = SOMEWHAT;break;
                case R.id.enjoy_3:sample_56_enjoy = VERY_MUCH;break;
            }
            switch (widget_57_interesting.getCheckedRadioButtonId()) {
                case R.id.interesting_0:sample_57_interesting = NOT_AT_ALL;break;
                case R.id.interesting_1:sample_57_interesting = LITTLE;break;
                case R.id.interesting_2:sample_57_interesting = SOMEWHAT;break;
                case R.id.interesting_3:sample_57_interesting = VERY_MUCH;break;
            }
            switch (widget_58_concentrating.getCheckedRadioButtonId()) {
                case R.id.concentrating_0:sample_58_concentrating = NOT_AT_ALL;break;
                case R.id.concentrating_1:sample_58_concentrating = LITTLE;break;
                case R.id.concentrating_2:sample_58_concentrating = SOMEWHAT;break;
                case R.id.concentrating_3:sample_58_concentrating = VERY_MUCH;break;
            }
            switch (widget_59_expectations.getCheckedRadioButtonId()) {
                case R.id.expectations_0:sample_59_expectations = NOT_AT_ALL;break;
                case R.id.expectations_1:sample_59_expectations = LITTLE;break;
                case R.id.expectations_2:sample_59_expectations = SOMEWHAT;break;
                case R.id.expectations_3:sample_59_expectations = VERY_MUCH;break;
            }
            switch (widget_60_control.getCheckedRadioButtonId()) {
                case R.id.control_0:sample_60_control = NOT_AT_ALL;break;
                case R.id.control_1:sample_60_control = LITTLE;break;
                case R.id.control_2:sample_60_control = SOMEWHAT;break;
                case R.id.control_3:sample_60_control = VERY_MUCH;break;
            }
            switch (widget_61_involved.getCheckedRadioButtonId()) {
                case R.id.involved_0:sample_61_involved = NOT_AT_ALL;break;
                case R.id.involved_1:sample_61_involved = LITTLE;break;
                case R.id.involved_2:sample_61_involved = SOMEWHAT;break;
                case R.id.involved_3:sample_61_involved = VERY_MUCH;break;
            }
            switch (widget_62_deal.getCheckedRadioButtonId()) {
                case R.id.deal_0:sample_62_deal = NOT_AT_ALL;break;
                case R.id.deal_1:sample_62_deal = LITTLE;break;
                case R.id.deal_2:sample_62_deal = SOMEWHAT;break;
                case R.id.deal_3:sample_62_deal = VERY_MUCH;break;
            }
            switch (widget_63_important.getCheckedRadioButtonId()) {
                case R.id.important_0:sample_63_important = NOT_AT_ALL;break;
                case R.id.important_1:sample_63_important = LITTLE;break;
                case R.id.important_2:sample_63_important = SOMEWHAT;break;
                case R.id.important_3:sample_63_important = VERY_MUCH;break;
            }
            switch (widget_64_others_expectations.getCheckedRadioButtonId()) {
                case R.id.others_expectations_0:sample_64_others_expectations = NOT_AT_ALL;break;
                case R.id.others_expectations_1:sample_64_others_expectations = LITTLE;break;
                case R.id.others_expectations_2:sample_64_others_expectations = SOMEWHAT;break;
                case R.id.others_expectations_3:sample_64_others_expectations = VERY_MUCH;break;
            }
            switch (widget_65_succeeding.getCheckedRadioButtonId()) {
                case R.id.succeeding_0:sample_65_succeeding = NOT_AT_ALL;break;
                case R.id.succeeding_1:sample_65_succeeding = LITTLE;break;
                case R.id.succeeding_2:sample_65_succeeding = SOMEWHAT;break;
                case R.id.succeeding_3:sample_65_succeeding = VERY_MUCH;break;
            }
            switch (widget_66_something_else.getCheckedRadioButtonId()) {
                case R.id.something_else_0:sample_66_something_else = NOT_AT_ALL;break;
                case R.id.something_else_1:sample_66_something_else = LITTLE;break;
                case R.id.something_else_2:sample_66_something_else = SOMEWHAT;break;
                case R.id.something_else_3:sample_66_something_else = VERY_MUCH;break;
            }
            switch (widget_67_feel_good.getCheckedRadioButtonId()) {
                case R.id.feel_good_0:sample_67_feel_good = NOT_AT_ALL;break;
                case R.id.feel_good_1:sample_67_feel_good = LITTLE;break;
                case R.id.feel_good_2:sample_67_feel_good = SOMEWHAT;break;
                case R.id.feel_good_3:sample_67_feel_good = VERY_MUCH;break;
            }

            //Others
            // Get max id registered in DB
            sampleDatabase = Room.databaseBuilder(getApplicationContext(), SampleDatabase.class, "sampledb").build(); //allowMainThreadQueries() removed
            int max_id = 0;
            List<DB_Entity> samples = sampleDatabase.myDao().getSamples();
            if (samples != null) {
                for (DB_Entity spl : samples) {
                    int spl_id = spl.getId();
                    if (spl_id > max_id)
                        max_id = spl_id;
                }
            }

            // set values not entered by user
            id = max_id + 1;
            SharedPreferences sharedPreferences = getSharedPreferences("FlowTest", MODE_PRIVATE);
            survey_num = sharedPreferences.getInt("current_survey_id", 1);
            sample_num = sharedPreferences.getInt("next_sample_id", 1);
            sample_date = Calendar.getInstance().getTimeInMillis();

            //GPS data
            latitude = Double.MIN_VALUE;
            longitude = Double.MIN_VALUE;

            //Flow Score
            int total_score = sample_56_enjoy
                    + sample_57_interesting
                    + sample_58_concentrating
                    + sample_59_expectations
                    + sample_60_control
                    + sample_61_involved
                    + sample_62_deal
                    + sample_63_important
                    + sample_64_others_expectations
                    + sample_65_succeeding
                    - sample_66_something_else
                    + sample_67_feel_good;

            // save parameters in a DB Entity
            DB_Entity completeSample = new DB_Entity();
            completeSample.setCompleteSample(id,
             survey_num,
             sample_num,
             sample_date,
             latitude,
             longitude,
             sample_02_where,
             sample_03_what,
             sample_04_what_else,
             sample_05_mind,
             sample_12_alone_yes,
             sample_15_spouse,
             sample_16_boss,
             sample_17_coworker,
             sample_18_friends,
             sample_19_girl_boyfriend,
             sample_20_mother,
             sample_21_father,
             sample_22_teacher,
             sample_23_classmate,
             sample_24_other,
             sample_25_children,
             sample_26_children_who,
             sample_27_siblings,
             sample_28_siblings_who,
             sample_43_wanted,
             sample_44_had,
             sample_45_nothing_else,
             sample_56_enjoy,
             sample_57_interesting,
             sample_58_concentrating,
             sample_59_expectations,
             sample_60_control,
             sample_61_involved,
             sample_62_deal,
             sample_63_important,
             sample_64_others_expectations,
             sample_65_succeeding,
             sample_66_something_else,
             sample_67_feel_good,
                    total_score);

            //save the sample
            sampleDatabase.myDao().addSample(completeSample);
        }

        public void survey_end_dialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(SampleActivity.this)
                    .setTitle(getString(R.string.survey_end_title))
                    .setMessage(getString(R.string.send_results))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            export();
                            see_result_dialog();
                        }
                    })
                    .setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            see_result_dialog();
                        }
                    })
                    .setView(R.layout.dialog_layout);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        public void see_result_dialog(){
            AlertDialog.Builder builder_results = new AlertDialog.Builder(SampleActivity.this)
                    .setTitle(getString(R.string.survey_end_title))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.survey_end_seeresults),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(getString(R.string.survey_end_later),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                        }
                    })
                    .setView(R.layout.dialog_layout);

            AlertDialog alertDialogResults = builder_results.create();
            alertDialogResults.show();
        }


        public void intermediate_dialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(SampleActivity.this)
                    .setTitle(getString(R.string.intermediate_title))
                    .setMessage(getString(R.string.intermediate_prompt))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.intermediate_seeresults),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(getString(R.string.intermediate_later),new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            finish();
                        }
                    })
                    .setView(R.layout.dialog_layout);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }


    public void export(){

        // generate data

        StringBuilder data = new StringBuilder();
        data.append("id;survey_num;sample_num;sample_date;latitude;longitude;sample_02_where;sample_03_what;sample_04_what_else;sample_05_mind;sample_12_alone_yes;sample_15_spouse; sample_16_boss;sample_17_coworker;sample_18_friends;sample_19_girl_boyfriend;sample_20_mother;sample_21_father;sample_22_teacher;sample_23_classmate;sample_24_other;sample_25_children;sample_26_children_who;sample_27_siblings;sample_28_siblings_who;sample_43_wanted;sample_44_had;sample_45_nothing_else;sample_56_enjoy;sample_57_interesting;sample_58_concentrating;sample_59_expectations;sample_60_control;sample_61_involved;sample_62_deal;sample_63_important;sample_64_others_expectations;sample_65_succeeding;sample_66_something_else;sample_67_feel_good");

        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamples();

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

            String dateString = new SimpleDateFormat("EEE MMM dd, yyyy HH:mm").format(sample_date);
            data_line = "\n"+ id+";"+survey_num+";"+sample_num+";"+dateString+";"+latitude+";"+longitude+";"+sample_02_where+";"+sample_03_what+";"+sample_04_what_else+";"+sample_05_mind+";"+sample_12_alone_yes+";"+sample_15_spouse+";"+sample_16_boss+";"+sample_17_coworker+";"+sample_18_friends+";"+sample_19_girl_boyfriend+";"+sample_20_mother+";"+sample_21_father+";"+sample_22_teacher+";"+sample_23_classmate+";"+sample_24_other+";"+sample_25_children+";"+sample_26_children_who+";"+sample_27_siblings+";"+sample_28_siblings_who+";"+sample_43_wanted+";"+sample_44_had+";"+sample_45_nothing_else+";"+sample_56_enjoy+";"+sample_57_interesting+";"+sample_58_concentrating+";"+sample_59_expectations+";"+sample_60_control+";"+sample_61_involved+";"+sample_62_deal+";"+sample_63_important+";"+sample_64_others_expectations+";"+sample_65_succeeding+";"+sample_66_something_else+";"+sample_67_feel_good;

            data.append(data_line);

        }


        try{
            //saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(data.toString().getBytes());
            out.close();

            //exporting
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.RS.flowtest.fileprovider", filelocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}


