package com.RS.flowtest.Results;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.RS.flowtest.DataBase.SampleDatabase;
import com.RS.flowtest.MainActivity;
import com.RS.flowtest.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class ResultFragment extends Fragment {

    private ScrollView result_scroll_view;

    private int survey_nb;

    private Boolean what_bar_open = FALSE;
    private ImageView what_arrow_up;
    private ImageView what_arrow_down ;
    private Boolean who_bar_open = FALSE;
    private ImageView who_arrow_up;
    private ImageView who_arrow_down ;
    private Boolean where_bar_open = FALSE;
    private ImageView where_arrow_up;
    private ImageView where_arrow_down ;
    private Boolean why_bar_open = FALSE;
    private ImageView why_arrow_up;
    private ImageView why_arrow_down ;
    private Boolean when_bar_open = FALSE;
    private  ImageView when_arrow_up;
    private ImageView when_arrow_down ;

    private PieChart alone_pieChart;
    private PieChart why_pieChart;
    private BarChart daysBarchart;

    private TextView top_what;
    private TextView top_who;
    private TextView top_where;
    private TextView best_day;


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        result_scroll_view = view.findViewById(R.id.result_scroll_view);

        TextView nb_of_samplesTextView= view.findViewById(R.id.nb_of_samples);
        Button button_details= view.findViewById(R.id.button_details);
        button_details.setOnClickListener(detailsListener);

        top_what = view.findViewById(R.id.top_what);
        top_who =view.findViewById(R.id.top_who);
        top_where=view.findViewById(R.id.top_where);
        best_day = view.findViewById(R.id.best_day);


        //initialize menus
        what_arrow_up = view.findViewById(R.id.what_arrow_up);
        what_arrow_down = view.findViewById(R.id.what_arrow_down);
        LinearLayout what_bar = view.findViewById(R.id.what_bar);
        what_bar.setOnClickListener(barListener);
        who_arrow_up = view.findViewById(R.id.who_arrow_up);
        who_arrow_down = view.findViewById(R.id.who_arrow_down);
        LinearLayout who_bar = view.findViewById(R.id.who_bar);
        who_bar.setOnClickListener(barListener);
        where_arrow_up = view.findViewById(R.id.where_arrow_up);
        where_arrow_down = view.findViewById(R.id.where_arrow_down);
        LinearLayout where_bar = view.findViewById(R.id.where_bar);
        where_bar.setOnClickListener(barListener);
        why_arrow_up = view.findViewById(R.id.why_arrow_up);
        why_arrow_down = view.findViewById(R.id.why_arrow_down);
        LinearLayout why_bar = view.findViewById(R.id.why_bar);
        why_bar.setOnClickListener(barListener);
        when_arrow_up = view.findViewById(R.id.when_arrow_up);
        when_arrow_down = view.findViewById(R.id.when_arrow_down);
        LinearLayout when_bar = view.findViewById(R.id.when_bar);
        when_bar.setOnClickListener(barListener);

        // alone piechart
        alone_pieChart = view.findViewById(R.id.pie_alone);
        alone_pieChart.setUsePercentValues(true);
        alone_pieChart.getDescription().setEnabled(false);
        alone_pieChart.setCenterText(getString(R.string.alone));
        alone_pieChart.getLegend().setEnabled(false);
        alone_pieChart.setEntryLabelColor(R.color.text_dark);

        List <PieEntry> alonePieEntries =new ArrayList<>();
        alonePieEntries.add(new PieEntry(getArguments().getInt("nb_alone_yes"),getString(R.string.yes)));
        alonePieEntries.add(new PieEntry(getArguments().getInt("nb_alone_no"),getString(R.string.no)));

        PieDataSet alonePieDataSet = new PieDataSet(alonePieEntries,"Where you alone?");
        alonePieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        alonePieDataSet.setDrawValues(false);

        PieData alonePieData = new PieData(alonePieDataSet);
        alone_pieChart.setData(alonePieData);



        // why piechart
        why_pieChart = view.findViewById(R.id.pie_why);
        why_pieChart.setUsePercentValues(true);
        why_pieChart.getDescription().setEnabled(false);
        why_pieChart.setCenterText(getString(R.string.why));
        why_pieChart.getLegend().setEnabled(false);
        why_pieChart.setEntryLabelColor(R.color.text_dark);


        List <PieEntry> whyPieEntries =new ArrayList<>();
        whyPieEntries.add(new PieEntry(getArguments().getInt("nb_wanted"),getString(R.string.sample_43_wanted)));
        whyPieEntries.add(new PieEntry(getArguments().getInt("nb_had"),getString(R.string.sample_44_had)));
        whyPieEntries.add(new PieEntry(getArguments().getInt("nb_nothing_else"),getString(R.string.sample_45_nothing_else)));

        PieDataSet whyPieDataSet = new PieDataSet(whyPieEntries,getString(R.string.why));
        whyPieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        whyPieDataSet.setDrawValues(false);

        PieData whyPieData = new PieData(whyPieDataSet);
        why_pieChart.setData(whyPieData);

        // day barchart
        daysBarchart = view.findViewById(R.id.days_barchart);
        daysBarchart.getDescription().setEnabled(false);
        daysBarchart.setPinchZoom(false);
        daysBarchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        daysBarchart.getXAxis().setDrawGridLines(false);
       // daysBarchart.getAxisLeft().setDrawGridLines(false);
        daysBarchart.getAxisLeft().setEnabled(false);
        daysBarchart.getAxisRight().setEnabled(false);
        daysBarchart.getLegend().setEnabled(false);

        final String[] weekdays = getResources().getStringArray(R.array.weekdays); // Your List / array with String Values For X-axis Labels
        // Set the value formatter
        XAxis xAxis = daysBarchart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));

        ArrayList<BarEntry> daysBarDataVals = getArguments().getParcelableArrayList("daysBarDataVals");


        BarDataSet daysBarDataSet = new BarDataSet(daysBarDataVals,"Days of the week");
        daysBarDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        daysBarDataSet.setDrawValues(false);
        BarData daysBarData = new BarData();
        daysBarData.addDataSet(daysBarDataSet);
        daysBarchart.setData((daysBarData));

        // Set values from Collection Adapter to the different views
        survey_nb = getArguments().getInt("survey_nb");
        nb_of_samplesTextView.setText(getArguments().getString("nb_of_samples"));
        top_what.setText(getArguments().getString("top_what"));
        top_where.setText(getArguments().getString("top_where"));
        top_who.setText(getArguments().getString("top_who"));

        return view;
    }


    private View.OnClickListener barListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.what_bar:
                    if(what_bar_open==FALSE)
                    {
                        what_arrow_up.setVisibility(View.VISIBLE);
                        what_arrow_down.setVisibility(View.GONE);
                        what_bar_open=TRUE;
                        top_what.setVisibility(View.VISIBLE);
                        //alone_pieChart.setVisibility(View.VISIBLE);
                        //alone_pieChart.animateXY(600,600);
                    }else
                    {
                        what_arrow_up.setVisibility(View.GONE);
                        what_arrow_down.setVisibility(View.VISIBLE);
                        what_bar_open=FALSE;
                        top_what.setVisibility(View.GONE);
                        //alone_pieChart.setVisibility(View.GONE);
                    }
                    break;
                case R.id.who_bar:
                    if(who_bar_open==FALSE)
                    {
                        who_arrow_up.setVisibility(View.VISIBLE);
                        who_arrow_down.setVisibility(View.GONE);
                        who_bar_open=TRUE;
                        alone_pieChart.setVisibility(View.VISIBLE);
                        alone_pieChart.animateXY(600,600);
                        top_who.setVisibility(View.VISIBLE);

                        if(MainActivity.DEV_MODE) {
                            result_scroll_view.smoothScrollTo(0, who_arrow_up.getTop());
                            Log.d("Flow_Values", "scroll to position Y = " + who_arrow_up.getTop());
                        }
                    }else
                        {
                        who_arrow_up.setVisibility(View.GONE);
                        who_arrow_down.setVisibility(View.VISIBLE);
                        who_bar_open=FALSE;
                        alone_pieChart.setVisibility(View.GONE);
                        top_who.setVisibility(View.GONE);
                        }
                    break;
                case R.id.where_bar:
                    if(where_bar_open==FALSE)
                    {
                        where_arrow_up.setVisibility(View.VISIBLE);
                        where_arrow_down.setVisibility(View.GONE);
                        where_bar_open=TRUE;
                        top_where.setVisibility(View.VISIBLE);

                        if(MainActivity.DEV_MODE) {
                            result_scroll_view.smoothScrollTo(0, top_where.getBottom());
                            Log.d("Flow_Values", "scroll to position Y = " + top_where.getBottom());
                        }
                    }else
                    {
                        where_arrow_up.setVisibility(View.GONE);
                        where_arrow_down.setVisibility(View.VISIBLE);
                        where_bar_open=FALSE;
                        top_where.setVisibility(View.GONE);
                    }
                    break;
                case R.id.why_bar:
                    if(why_bar_open==FALSE)
                    {
                        why_arrow_up.setVisibility(View.VISIBLE);
                        why_arrow_down.setVisibility(View.GONE);
                        why_bar_open=TRUE;
                        why_pieChart.setVisibility(View.VISIBLE);
                        why_pieChart.animateXY(600,600);

                        if(MainActivity.DEV_MODE) {
                            result_scroll_view.smoothScrollTo(0, why_pieChart.getBottom());
                            Log.d("Flow_Values", "scroll to position Y = " + why_pieChart.getBottom());
                        }
                    }else
                        {
                        why_arrow_up.setVisibility(View.GONE);
                        why_arrow_down.setVisibility(View.VISIBLE);
                        why_bar_open=FALSE;
                        why_pieChart.setVisibility(View.GONE);
                    }
                    break;
                case R.id.when_bar:
                    if(when_bar_open==FALSE)
                    {
                        when_arrow_up.setVisibility(View.VISIBLE);
                        when_arrow_down.setVisibility(View.GONE);
                        when_bar_open=TRUE;
                        best_day.setVisibility(View.VISIBLE);
                        daysBarchart.animateY(600);
                        daysBarchart.setVisibility(View.VISIBLE);
                        int[] location = new int[2];
                        when_arrow_up.getLocationInWindow(location);

                        if(MainActivity.DEV_MODE) {
                            result_scroll_view.smoothScrollTo(location[0], location[1]);
                            Log.d("Flow_Values", "scroll to position Y = " + location[1]);
                        }
                    }else
                    {
                        when_arrow_up.setVisibility(View.GONE);
                        when_arrow_down.setVisibility(View.VISIBLE);
                        when_bar_open=FALSE;
                        best_day.setVisibility(View.GONE);
                        daysBarchart.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };


    private View.OnClickListener detailsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FlowTest",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("result_survey_id", survey_nb);
            editor.apply();

            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            startActivity(intent);;


        }
    };

}
