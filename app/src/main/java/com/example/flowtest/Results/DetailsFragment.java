package com.example.flowtest.Results;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flowtest.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;


public class DetailsFragment extends Fragment {

    private TextView detailedResultTextView;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        detailedResultTextView = view.findViewById(R.id.detailed_result_text);




        //get which survey the user wants detailed about
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FlowTest",MODE_PRIVATE);
        int result_survey_id = sharedPreferences.getInt("result_survey_id",1);
        Log.d("Flow_value","Result survey id = "+result_survey_id);


        String message = getArguments().getString("message");
        Log.d("Flow_Value","detailedResultTextView = "+message);
        detailedResultTextView.setText(message);

        //detailedResultTextView.setText("Number of samples in this survey = "+samples.size()+"\n position = "+position);

        return view;
    }



}
