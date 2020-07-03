package com.RS.flowtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoPopActivity extends Activity {

    int current_page;
    ImageView nextButton;
    ImageView prevButton;

    TextView popTitle;
    TextView popPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pop);

        current_page = 1;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.7));


        Button closeButton = findViewById(R.id.popup_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextButton = findViewById(R.id.popup_next);
        nextButton.setOnClickListener(navListener);
        prevButton = findViewById(R.id.popup_prev);
        prevButton.setOnClickListener(navListener);
        prevButton.setVisibility(View.INVISIBLE);

        popTitle=findViewById(R.id.pop_title);
        popPage=findViewById(R.id.pop_page);

    }



    private View.OnClickListener navListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            int direction;
            switch(v.getId()) {
                //PREVIOUS BUTTON
                case R.id.popup_prev:
                    if (current_page > 1)
                        current_page--;
                    break;

                //NEXT BUTTON
                case R.id.popup_next:
                    if (current_page <= 3)
                        current_page++;
                    break;
            }
            if(current_page==1) {
                popTitle.setText(R.string.popup_what_flow_title);
                popPage.setText(R.string.popup_what_flow);
                nextButton.setVisibility(View.VISIBLE);
                prevButton.setVisibility(View.INVISIBLE);
            }else if(current_page==2){
                popTitle.setText(R.string.popup_why_flow_title);
                popPage.setText(R.string.popup_why_flow);
                nextButton.setVisibility(View.VISIBLE);
                prevButton.setVisibility(View.VISIBLE);
            }else if(current_page==3){
                popTitle.setText(R.string.popup_further_title);
                popPage.setText(R.string.popup_further);
                popPage.setMovementMethod(LinkMovementMethod.getInstance());
                nextButton.setVisibility(View.INVISIBLE);
                prevButton.setVisibility(View.VISIBLE);
            }
            if(MainActivity.DEV_MODE)
                Log.d("Flow_value","page = "+current_page);
        }
    };
}
