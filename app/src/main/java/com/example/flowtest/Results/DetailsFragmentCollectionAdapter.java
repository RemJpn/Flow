package com.example.flowtest.Results;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.flowtest.DataBase.DB_Entity;
import com.example.flowtest.MainActivity;
import com.example.flowtest.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class DetailsFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    Context mContext;
    final String FLOW_TEST = "FlowTest";

    public DetailsFragmentCollectionAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();

        //get the samples of the selected survey
        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesFromSurveyId(DetailsActivity.SELECTED_SURVEY);

        String info = "\n";

        DB_Entity spl = samples.get(position);

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

        String dateString = new SimpleDateFormat("MMM dd, yyyy HH:mm").format(sample_date);

        if(MainActivity.DEV_MODE)
            info += "Position = " + position +"\n"+"id: "+id+"\n"+"Survey n°: "+survey_num+"\n"+"Sample n°: "+sample_num+"\n"+"Date: "+dateString+"\n"+"Latitude: "+latitude+"\n"+"Longitude: "+ longitude+"\n";

        info += mContext.getString(R.string.sample_02_where)+"\n     "+sample_02_where+"\n"
                +mContext.getString(R.string.sample_03_what)+"\n     "+sample_03_what+"\n"
                +mContext.getString(R.string.sample_04_what_else)+"\n     "+sample_04_what_else+"\n"
                +mContext.getString(R.string.sample_05_mind)+"\n     "+sample_05_mind+"\n\n";

        info += mContext.getString(R.string.who)+"\n     ";
        if(sample_12_alone_yes)
            info += mContext.getString(R.string.alone);
        else{
            if(sample_15_spouse)
                info += mContext.getString(R.string.sample_15_spouse)+", ";
            if(sample_16_boss)
                info += mContext.getString(R.string.sample_16_boss)+", ";
            if(sample_17_coworker)
                info += mContext.getString(R.string.sample_17_coworker)+", ";
            if(sample_18_friends)
                info += mContext.getString(R.string.sample_18_friends)+", ";
            if(sample_19_girl_boyfriend)
                info += mContext.getString(R.string.sample_19_girl_boyfriend)+", ";
            if(sample_20_mother)
                info += mContext.getString(R.string.sample_20_mother)+", ";
            if(sample_21_father)
                info += mContext.getString(R.string.sample_21_father)+", ";
            if(sample_22_teacher)
                info += mContext.getString(R.string.sample_22_teacher)+", ";
            if(sample_23_classmate)
                info += mContext.getString(R.string.sample_23_classmate)+", ";
            if(sample_24_other)
                info += mContext.getString(R.string.sample_24_other)+", ";
            if(sample_25_children)
                info += mContext.getString(R.string.sample_25_children)+" " + sample_26_children_who + ", ";
            if(sample_27_siblings)
                info += mContext.getString(R.string.sample_27_siblings)+" "+ sample_28_siblings_who+", ";
        }
        info += "\n\n";

        info+= mContext.getString(R.string.why)+"\n     ";
        if(sample_43_wanted)
            info += mContext.getString(R.string.sample_43_wanted)+"\n\n";
        if(sample_44_had)
            info += mContext.getString(R.string.sample_44_had)+"\n\n";
        if(sample_45_nothing_else)
            info += mContext.getString(R.string.sample_45_nothing_else)+"\n\n";

        info += mContext.getString(R.string.sample_56_enjoy) + " " + sample_56_enjoy+"\n";
        info += mContext.getString(R.string.sample_57_interesting) + " " + sample_57_interesting+"\n";
        info += mContext.getString(R.string.sample_58_concentrating) + " " + sample_58_concentrating+"\n";
        info += mContext.getString(R.string.sample_59_expectations) + " " + sample_59_expectations+"\n";
        info += mContext.getString(R.string.sample_60_control) + " " + sample_60_control+"\n";
        info += mContext.getString(R.string.sample_61_involved) + " " + sample_61_involved+"\n";
        info += mContext.getString(R.string.sample_62_deal) + " " + sample_62_deal+"\n";
        info += mContext.getString(R.string.sample_63_important) + " " + sample_63_important+"\n";
        info += mContext.getString(R.string.sample_64_others_expectations) + " " + sample_64_others_expectations+"\n";
        info += mContext.getString(R.string.sample_65_succeeding) + " " + sample_65_succeeding+"\n";
        info += mContext.getString(R.string.sample_66_something_else) + " " + sample_66_something_else+"\n";
        info += mContext.getString(R.string.sample_67_feel_good) + " " + sample_67_feel_good+"\n";

        info+= "----------------------";

        bundle.putString("message",info);

        detailsFragment.setArguments(bundle);

        return detailsFragment;    }

    @Override
    public int getCount() {
        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesFromSurveyId(DetailsActivity.SELECTED_SURVEY);


        return samples.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //get the samples of the selected survey
        List<DB_Entity> samples = MainActivity.sampleDatabase.myDao().getSamplesFromSurveyId(DetailsActivity.SELECTED_SURVEY);
        Long sample_date = samples.get(position).getSample_date();
        String dateString = new SimpleDateFormat("MMM dd, yyyy HH:mm").format(sample_date);

        return dateString;
    }
}
