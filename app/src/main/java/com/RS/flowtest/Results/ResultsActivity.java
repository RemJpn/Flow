package com.RS.flowtest.Results;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.RS.flowtest.R;


public class ResultsActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ResultFragmentCollectionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_screen);

        //initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.results));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        // initialize viewPager and adapter
        viewPager = findViewById(R.id.pager);
        adapter = new ResultFragmentCollectionAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);


    }

}
