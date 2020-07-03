package com.RS.flowtest.Results;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.RS.flowtest.MainActivity;
import com.RS.flowtest.R;

public class DetailsActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private DetailsFragmentCollectionAdapter adapter;

    public static int SELECTED_SURVEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_screen); //use same layout as result screen

        Intent intent =  getIntent();
        int survey_nb = intent.getIntExtra("survey_nb",1);

        //
        SharedPreferences sharedPreferences = DetailsActivity.this.getSharedPreferences("FlowTest",MODE_PRIVATE);
        SELECTED_SURVEY = sharedPreferences.getInt("result_survey_id",1);
        if(MainActivity.DEV_MODE)
            Log.d("Flow_value","SELECTED SURVEY = "+SELECTED_SURVEY);



        //initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.detailed_results));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initialize viewPager and adapter
        viewPager = findViewById(R.id.pager);
        adapter = new DetailsFragmentCollectionAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
    }
}
