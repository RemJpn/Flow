package com.example.flowtest.Results;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.flowtest.DataBase.DB_Entity;
import com.example.flowtest.MainActivity;
import com.example.flowtest.R;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ResultFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    int max_survey_id = 0;

    public ResultFragmentCollectionAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        int nb_alone_yes = 0;
        int nb_alone_no = 0;
        int nb_wanted = 0;
        int nb_had = 0;
        int nb_nothing_else = 0;


        // invert surveys to get latest first
        Log.d("Flow_value","position départ = "+position);
        Log.d("Flow_value","max survey id = "+max_survey_id);
        position = max_survey_id - position;
        Log.d("Flow_value","position inversée = "+position);


        //retrieving info from DB for sample "position", ordered by Descending score, in order to easily get information on most "in flow" conditions
        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesByScore(position);

        ArrayList<Integer> MondayScores = new ArrayList<Integer>() ;
        ArrayList<Integer> TuesdaysScores = new ArrayList<Integer>() ;
        ArrayList<Integer> WednesdaysScores = new ArrayList<Integer>() ;
        ArrayList<Integer> ThursdaysScores = new ArrayList<Integer>() ;
        ArrayList<Integer> FridaysScores = new ArrayList<Integer>() ;
        ArrayList<Integer> SaturdaysScores = new ArrayList<Integer>() ;
        ArrayList<Integer> SundaysScores = new ArrayList<Integer>() ;

        for ( DB_Entity spl : samples) {

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
            int sample_56_enjoy = spl.getSample_56_enjoy();
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
            int sample_67_feel_good = spl.getSample_67_feel_good();
            int total_score = spl.getTotal_score();

            if (sample_12_alone_yes == TRUE)
                nb_alone_yes ++;
            else
                nb_alone_no ++;

            if(sample_43_wanted)
                nb_wanted ++;
            else if(sample_44_had)
                nb_had ++;
            else if(sample_45_nothing_else)
                nb_nothing_else ++;

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(sample_date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // this will for example return 3 for tuesday
            switch (dayOfWeek){
                case 1:
                    SundaysScores.add(total_score);
                    break;
                case 2:
                    MondayScores.add(total_score);
                    break;
                case 3:
                    TuesdaysScores.add(total_score);
                    break;
                case 4:
                    WednesdaysScores.add(total_score);
                    break;
                case 5:
                    ThursdaysScores.add(total_score);
                    break;
                case 6:
                    FridaysScores.add(total_score);
                    break;
                case 7:
                    SaturdaysScores.add(total_score);
                    break;
            }


            //String dateString = new SimpleDateFormat("MM/dd/yyyy hh:mm").format(sample_date);

            //info = "id: " + id + "\n" + "Survey n°: " + survey_num + "\n" + "Sample n°: " + sample_num + "\n" + "Date: " + dateString + "\n" + latitude + "\n" + longitude + "\n" + sample_02_where + "\n" + sample_03_what + "\n" + sample_04_what_else + "\n" + sample_05_mind + "\n" + sample_12_alone_yes + "\n" + sample_15_spouse + "\n" + sample_16_boss + "\n" + sample_17_coworker + "\n" + sample_18_friends + "\n" + sample_19_girl_boyfriend + "\n" + sample_20_mother + "\n" + sample_21_father + "\n" + sample_22_teacher + "\n" + sample_23_classmate + "\n" + sample_24_other + "\n" + sample_25_children + "\n" + sample_26_children_who + "\n" + sample_27_siblings + "\n" + sample_28_siblings_who + "\n" + sample_43_wanted + "\n" + sample_44_had + "\n" + sample_45_nothing_else + "\n" + sample_56_enjoy + "\n" + sample_57_interesting + "\n" + sample_58_concentrating + "\n" + sample_59_expectations + "\n" + sample_60_control + "\n" + sample_61_involved + "\n" + sample_62_deal + "\n" + sample_63_important + "\n" + sample_64_others_expectations + "\n" + sample_65_succeeding + "\n" + sample_66_something_else + "\n" + sample_67_feel_good + "\n" + "----------------------";

        }

        float averageSunday=0;
        int sumSunday = 0;
        if(SundaysScores.size() != 0)
        {
            for (int m : SundaysScores)
                sumSunday += m;
            averageSunday = sumSunday / SundaysScores.size();
        }
        float averageMonday=0;
        int sumMonday = 0;
        if(MondayScores.size() != 0)
        {
            for (int m : MondayScores)
                sumMonday += m;
            averageMonday = sumMonday / MondayScores.size();
        }
        //Log.d("Flow_values","Average Monday score = "+averageMonday);
        float averageTuesday=0;
        int sumTuesday = 0;
        if(TuesdaysScores.size() != 0)
        {
            for (int m : TuesdaysScores)
                sumTuesday += m;
            averageTuesday = sumTuesday / TuesdaysScores.size();
        }
        float averageWednesday=0;
        int sumWednesday = 0;
        if(WednesdaysScores.size() != 0)
        {
            for (int m : WednesdaysScores)
                sumWednesday += m;
            averageWednesday = sumWednesday / WednesdaysScores.size();
        }
        float averageThursday=0;
        int sumThursday = 0;
        if(ThursdaysScores.size() != 0)
        {
            for (int m : ThursdaysScores)
                sumThursday += m;
            averageThursday = sumThursday / ThursdaysScores.size();
        }
        float averageFriday=0;
        int sumFriday = 0;
        if(FridaysScores.size() != 0)
        {
            for (int m : FridaysScores)
                sumFriday += m;
            averageFriday = sumFriday / FridaysScores.size();
        }
        float averageSaturday=0;
        int sumSaturday = 0;
        if(SaturdaysScores.size() != 0)
        {
            for (int m : SaturdaysScores)
                sumSaturday += m;
            averageSaturday = sumSaturday / SaturdaysScores.size();
        }

        ArrayList<BarEntry> daysBarDataVals = new ArrayList<BarEntry>();
        daysBarDataVals.add(new BarEntry(1,averageSunday));
        daysBarDataVals.add(new BarEntry(2,averageMonday));
        daysBarDataVals.add(new BarEntry(3,averageTuesday));
        daysBarDataVals.add(new BarEntry(4,averageWednesday));
        daysBarDataVals.add(new BarEntry(5,averageThursday));
        daysBarDataVals.add(new BarEntry(6,averageFriday));
        daysBarDataVals.add(new BarEntry(7,averageSaturday));


        // getting info about the 3 samples with the highest scores
        String top_what = "";
        String top_where = "";
        String top_who = "";

        int nb_of_samples = samples.size();
        if (nb_of_samples>2) {
            /*
            String messageLog = "Top ID / Score = \n";
            int[] top_id = new int[3];
            int[] top_score = new int[3];
            for (int i = 0; i < 3; i++) {
                top_id[i] = samples.get(i).getId();
                top_score[i] = samples.get(i).getTotal_score();
                messageLog = messageLog + top_id[i] + " / " + top_score[i]+"\n";
            }
            Log.d("Flow_values",messageLog);
            */
            top_what = mContext.getString(R.string.top_what);
            top_where = mContext.getString(R.string.top_where);
            top_who = mContext.getString(R.string.top_who);
            for (int i = 0; i < 3; i++) {
                top_what = top_what + "\n - " + samples.get(i).getSample_03_what();
                top_where = top_where + "\n - " + samples.get(i).getSample_02_where();
                top_who = top_who + "\n - ";
                if (samples.get(i).isSample_12_alone_yes())
                    top_who = top_who + mContext.getString(R.string.alone);
                else {   //concatenate all info entered by user for given samples
                    if (samples.get(i).isSample_15_spouse())
                        top_who = top_who + mContext.getString(R.string.sample_15_spouse) + ", ";
                    if (samples.get(i).isSample_16_boss())
                        top_who = top_who + mContext.getString(R.string.sample_16_boss) + ", ";
                    if (samples.get(i).isSample_17_coworker())
                        top_who = top_who + mContext.getString(R.string.sample_17_coworker) + ", ";
                    if (samples.get(i).isSample_18_friends())
                        top_who = top_who + mContext.getString(R.string.sample_18_friends) + ", ";
                    if (samples.get(i).isSample_19_girl_boyfriend())
                        top_who = top_who + mContext.getString(R.string.sample_19_girl_boyfriend) + ", ";
                    if (samples.get(i).isSample_20_mother())
                        top_who = top_who + mContext.getString(R.string.sample_20_mother) + ", ";
                    if (samples.get(i).isSample_21_father())
                        top_who = top_who + mContext.getString(R.string.sample_21_father) + ", ";
                    if (samples.get(i).isSample_22_teacher())
                        top_who = top_who + mContext.getString(R.string.sample_22_teacher) + ", ";
                    if (samples.get(i).isSample_23_classmate())
                        top_who = top_who + mContext.getString(R.string.sample_23_classmate) + ", ";
                    if (samples.get(i).isSample_24_other())
                        top_who = top_who + mContext.getString(R.string.sample_24_other) + ", ";
                    if (samples.get(i).isSample_25_children())
                        top_who = top_who + mContext.getString(R.string.sample_25_children) + ", ";
                    if (samples.get(i).isSample_27_siblings())
                        top_who = top_who + mContext.getString(R.string.sample_27_siblings) + ", ";
                }
            }

            /*String[] top_what = new String[3];
            for (int i = 0; i < 3; i++) {
                top_what[i] = samples.get(i).getSample_03_what();
            }
            String[] top_where = new String[3];
            for (int i = 0; i < 3; i++) {
                top_where[i] = samples.get(i).getSample_02_where();
            }
            String[] top_who = new String[3];
            for (int i = 0; i < 3; i++) {
                top_who[i]="";
                if (samples.get(i).isSample_12_alone_yes())
                    top_who[i] = mContext.getString(R.string.alone);
                else {   //concatenate all info entered by user for given samples
                    if (samples.get(i).isSample_15_spouse())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_15_spouse) + ", ";
                    if (samples.get(i).isSample_16_boss())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_16_boss) + ", ";
                    if (samples.get(i).isSample_17_coworker())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_17_coworker) + ", ";
                    if (samples.get(i).isSample_18_friends())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_18_friends) + ", ";
                    if (samples.get(i).isSample_19_girl_boyfriend())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_19_girl_boyfriend) + ", ";
                    if (samples.get(i).isSample_20_mother())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_20_mother) + ", ";
                    if (samples.get(i).isSample_21_father())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_21_father) + ", ";
                    if (samples.get(i).isSample_22_teacher())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_22_teacher) + ", ";
                    if (samples.get(i).isSample_23_classmate())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_23_classmate) + ", ";
                    if (samples.get(i).isSample_24_other())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_24_other) + ", ";
                    if (samples.get(i).isSample_25_children())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_25_children) + ", ";
                    if (samples.get(i).isSample_27_siblings())
                        top_who[i] = top_who[i] + mContext.getString(R.string.sample_27_siblings) + ", ";
                }
            }

            Log.d("Flow_values", "Top what = \n" + top_what[0] + "\n" + top_what[1] + "\n" + top_what[2] + "\nTop where = \n" + top_where[0] + "\n" + top_where[1] + "\n" + top_where[2]);
            Log.d("Flow_values", "Top who = \n" + top_who[0] + "\n" + top_who[1] + "\n" + top_who[2]);
            */

        }

        // Provide the values to a bundle, to be used in ResultFragment
        bundle.putParcelableArrayList("daysBarDataVals",daysBarDataVals);
        bundle.putString("nb_of_samples",mContext.getString(R.string.number_of_samples)+nb_of_samples);
        bundle.putInt("survey_nb",position);
        bundle.putString("top_what",top_what);
        bundle.putString("top_where",top_where);
        bundle.putString("top_who",top_who);
        bundle.putInt("nb_alone_yes",nb_alone_yes);
        bundle.putInt("nb_alone_no", nb_alone_no);
        bundle.putInt("nb_wanted",nb_wanted);
        bundle.putInt("nb_had", nb_had);
        bundle.putInt("nb_nothing_else", nb_nothing_else);
        resultFragment.setArguments(bundle);

        return resultFragment;
    }

    @Override
    public int getCount() {
        // Get max survey id registered in DB

        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamples();
        for(DB_Entity spl : samples)
        {
            int survey_id = spl.getSurvey_num();
            if (survey_id > max_survey_id)
                max_survey_id = survey_id;
        }
        return max_survey_id;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        position = max_survey_id - position;

        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesFromSurveyId(position);

        if (samples.size() != 0) {

            Long start_date = samples.get(0).getSample_date();
            Long end_date = samples.get(0).getSample_date();

            for (DB_Entity spl : samples) {
                long temp_date = spl.getSample_date();
                if (temp_date > end_date)
                    end_date = temp_date;
                else if (temp_date < start_date)
                    start_date = temp_date;
            }

            String startDateString = new SimpleDateFormat("MMM dd, yyyy").format(start_date);
            String endDateString = new SimpleDateFormat("MMM dd, yyyy").format(end_date);

            return startDateString + " - " + endDateString;
        }else
            return "empty";

    }
}
