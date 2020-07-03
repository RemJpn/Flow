package com.RS.flowtest.DataBase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "samples")
public class DB_Entity {

    //version 1: initial
    //version 2: added total_score

    @PrimaryKey
    private long sample_date;

    private int id;

    private int survey_num;

    private int sample_num;

    private double longitude;
    private double latitude;


    //---------Données utilisateurs-----
    // 1st part
    private String sample_02_where;
    private String sample_03_what;
    private String sample_04_what_else;
    private String sample_05_mind;

    // 2nd part
    private boolean sample_12_alone_yes;
    private boolean sample_15_spouse;
    private boolean sample_16_boss;
    private boolean sample_17_coworker;
    private boolean sample_18_friends;
    private boolean sample_19_girl_boyfriend;
    private boolean sample_20_mother;
    private boolean sample_21_father;
    private boolean sample_22_teacher;
    private boolean sample_23_classmate;
    private boolean sample_24_other;
    private boolean sample_25_children;
    private String sample_26_children_who;
    private boolean sample_27_siblings;
    private String sample_28_siblings_who;

    // 3rd part
    private boolean sample_43_wanted;
    private boolean sample_44_had;
    private boolean sample_45_nothing_else;

    // 4th part
    // Not at all = 1 ; a little = 2 ; Somewhat = 3; Very much = 4
    private int sample_56_enjoy;
    private int sample_57_interesting;
    private int sample_58_concentrating;
    private int sample_59_expectations;
    private int sample_60_control;
    private int sample_61_involved;
    private int sample_62_deal;
    private int sample_63_important;
    private int sample_64_others_expectations;
    private int sample_65_succeeding;
    private int sample_66_something_else;
    private int sample_67_feel_good;



    private int total_score;


    public void setCompleteSample(   int id,
                                     int survey_num,
                                     int sample_num,
                                     long sample_date,
                                     double latitude,
                                     double longitude,
                                     String sample_02_where,
                                     String sample_03_what,
                                     String sample_04_what_else,
                                     String sample_05_mind,
                                     boolean sample_12_alone_yes,
                                     boolean sample_15_spouse,
                                     boolean sample_16_boss,
                                     boolean sample_17_coworker,
                                     boolean sample_18_friends,
                                     boolean sample_19_girl_boyfriend,
                                     boolean sample_20_mother,
                                     boolean sample_21_father,
                                     boolean sample_22_teacher,
                                     boolean sample_23_classmate,
                                     boolean sample_24_other,
                                     boolean sample_25_children,
                                     String sample_26_children_who,
                                     boolean sample_27_siblings,
                                     String sample_28_siblings_who,
                                     boolean sample_43_wanted,
                                     boolean sample_44_had,
                                     boolean sample_45_nothing_else,
                                     int sample_56_enjoy,
                                     int sample_57_interesting,
                                     int sample_58_concentrating,
                                     int sample_59_expectations,
                                     int sample_60_control,
                                     int sample_61_involved,
                                     int sample_62_deal,
                                     int sample_63_important,
                                     int sample_64_others_expectations,
                                     int sample_65_succeeding,
                                     int sample_66_something_else,
                                     int sample_67_feel_good,
                                     int total_score)
    {
        this.id = id;
        this.survey_num =survey_num;
        this.sample_num = sample_num;
        this.sample_date =sample_date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sample_02_where =sample_02_where;
        this.sample_03_what= sample_03_what;
        this.sample_04_what_else= sample_04_what_else;
        this.sample_05_mind=sample_05_mind;
        this.sample_12_alone_yes=sample_12_alone_yes;
        this.sample_15_spouse=sample_15_spouse;
        this.sample_16_boss=sample_16_boss;
        this.sample_17_coworker=sample_17_coworker;
        this.sample_18_friends=sample_18_friends;
        this.sample_19_girl_boyfriend=sample_19_girl_boyfriend;
        this.sample_20_mother=sample_20_mother;
        this.sample_21_father=sample_21_father;
        this.sample_22_teacher=sample_22_teacher;
        this.sample_23_classmate=sample_23_classmate;
        this.sample_24_other=sample_24_other;
        this.sample_25_children=sample_25_children;
        this.sample_26_children_who=sample_26_children_who;
        this.sample_27_siblings=sample_27_siblings;
        this.sample_28_siblings_who=sample_28_siblings_who;
        this.sample_43_wanted=sample_43_wanted;
        this.sample_44_had=sample_44_had;
        this.sample_45_nothing_else=sample_45_nothing_else;
        this.sample_56_enjoy=sample_56_enjoy;
        this.sample_57_interesting=sample_57_interesting;
        this.sample_58_concentrating=sample_58_concentrating;
        this.sample_59_expectations=sample_59_expectations;
        this.sample_60_control=sample_60_control;
        this.sample_61_involved=sample_61_involved;
        this.sample_62_deal=sample_62_deal;
        this.sample_63_important=sample_63_important;
        this.sample_64_others_expectations=sample_64_others_expectations;
        this.sample_65_succeeding=sample_65_succeeding;
        this.sample_66_something_else=sample_66_something_else;
        this.sample_67_feel_good=sample_67_feel_good;
        this.total_score=total_score;

    }


    //---------individual Getters and Setters------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurvey_num() {
        return survey_num;
    }

    public void setSurvey_num(int survey_num) {
        this.survey_num = survey_num;
    }

    public int getSample_num() {
        return sample_num;
    }

    public void setSample_num(int sample_num) {
        this.sample_num = sample_num;
    }

    public long getSample_date() {
        return sample_date;
    }

    public void setSample_date(long sample_date) {
        this.sample_date = sample_date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getSample_02_where() {
        return sample_02_where;
    }

    public void setSample_02_where(String sample_02_where) {
        this.sample_02_where = sample_02_where;
    }

    public String getSample_03_what() {
        return sample_03_what;
    }

    public void setSample_03_what(String sample_03_what) {
        this.sample_03_what = sample_03_what;
    }

    public String getSample_04_what_else() {
        return sample_04_what_else;
    }

    public void setSample_04_what_else(String sample_04_what_else) {
        this.sample_04_what_else = sample_04_what_else;
    }

    public String getSample_05_mind() {
        return sample_05_mind;
    }

    public void setSample_05_mind(String sample_05_mind) {
        this.sample_05_mind = sample_05_mind;
    }

    public boolean isSample_12_alone_yes() {
        return sample_12_alone_yes;
    }

    public void setSample_12_alone_yes(boolean sample_12_alone_yes) {
        this.sample_12_alone_yes = sample_12_alone_yes;
    }

    public boolean isSample_15_spouse() {
        return sample_15_spouse;
    }

    public void setSample_15_spouse(boolean sample_15_spouse) {
        this.sample_15_spouse = sample_15_spouse;
    }

    public boolean isSample_16_boss() {
        return sample_16_boss;
    }

    public void setSample_16_boss(boolean sample_16_boss) {
        this.sample_16_boss = sample_16_boss;
    }

    public boolean isSample_17_coworker() {
        return sample_17_coworker;
    }

    public void setSample_17_coworker(boolean sample_17_coworker) {
        this.sample_17_coworker = sample_17_coworker;
    }

    public boolean isSample_18_friends() {
        return sample_18_friends;
    }

    public void setSample_18_friends(boolean sample_18_friends) {
        this.sample_18_friends = sample_18_friends;
    }

    public boolean isSample_19_girl_boyfriend() {
        return sample_19_girl_boyfriend;
    }

    public void setSample_19_girl_boyfriend(boolean sample_19_girl_boyfriend) {
        this.sample_19_girl_boyfriend = sample_19_girl_boyfriend;
    }

    public boolean isSample_20_mother() {
        return sample_20_mother;
    }

    public void setSample_20_mother(boolean sample_20_mother) {
        this.sample_20_mother = sample_20_mother;
    }

    public boolean isSample_21_father() {
        return sample_21_father;
    }

    public void setSample_21_father(boolean sample_21_father) {
        this.sample_21_father = sample_21_father;
    }

    public boolean isSample_22_teacher() {
        return sample_22_teacher;
    }

    public void setSample_22_teacher(boolean sample_22_teacher) {
        this.sample_22_teacher = sample_22_teacher;
    }

    public boolean isSample_23_classmate() {
        return sample_23_classmate;
    }

    public void setSample_23_classmate(boolean sample_23_classmate) {
        this.sample_23_classmate = sample_23_classmate;
    }

    public boolean isSample_24_other() {
        return sample_24_other;
    }

    public void setSample_24_other(boolean sample_24_other) {
        this.sample_24_other = sample_24_other;
    }

    public boolean isSample_25_children() {
        return sample_25_children;
    }

    public void setSample_25_children(boolean sample_25_children) {
        this.sample_25_children = sample_25_children;
    }

    public String getSample_26_children_who() {
        return sample_26_children_who;
    }

    public void setSample_26_children_who(String sample_26_children_who) {
        this.sample_26_children_who = sample_26_children_who;
    }

    public boolean isSample_27_siblings() {
        return sample_27_siblings;
    }

    public void setSample_27_siblings(boolean sample_27_siblings) {
        this.sample_27_siblings = sample_27_siblings;
    }

    public String getSample_28_siblings_who() {
        return sample_28_siblings_who;
    }

    public void setSample_28_siblings_who(String sample_28_siblings_who) {
        this.sample_28_siblings_who = sample_28_siblings_who;
    }

    public boolean isSample_43_wanted() {
        return sample_43_wanted;
    }

    public void setSample_43_wanted(boolean sample_43_wanted) {
        this.sample_43_wanted = sample_43_wanted;
    }

    public boolean isSample_44_had() {
        return sample_44_had;
    }

    public void setSample_44_had(boolean sample_44_had) {
        this.sample_44_had = sample_44_had;
    }

    public boolean isSample_45_nothing_else() {
        return sample_45_nothing_else;
    }

    public void setSample_45_nothing_else(boolean sample_45_nothing_else) {
        this.sample_45_nothing_else = sample_45_nothing_else;
    }

    public int getSample_56_enjoy() {
        return sample_56_enjoy;
    }

    public void setSample_56_enjoy(int sample_56_enjoy) {
        this.sample_56_enjoy = sample_56_enjoy;
    }

    public int getSample_57_interesting() {
        return sample_57_interesting;
    }

    public void setSample_57_interesting(int sample_57_interesting) {
        this.sample_57_interesting = sample_57_interesting;
    }

    public int getSample_58_concentrating() {
        return sample_58_concentrating;
    }

    public void setSample_58_concentrating(int sample_58_concentrating) {
        this.sample_58_concentrating = sample_58_concentrating;
    }

    public int getSample_59_expectations() {
        return sample_59_expectations;
    }

    public void setSample_59_expectations(int sample_59_expectations) {
        this.sample_59_expectations = sample_59_expectations;
    }

    public int getSample_60_control() {
        return sample_60_control;
    }

    public void setSample_60_control(int sample_60_control) {
        this.sample_60_control = sample_60_control;
    }

    public int getSample_61_involved() {
        return sample_61_involved;
    }

    public void setSample_61_involved(int sample_61_involved) {
        this.sample_61_involved = sample_61_involved;
    }

    public int getSample_62_deal() {
        return sample_62_deal;
    }

    public void setSample_62_deal(int sample_62_deal) {
        this.sample_62_deal = sample_62_deal;
    }

    public int getSample_63_important() {
        return sample_63_important;
    }

    public void setSample_63_important(int sample_63_important) {
        this.sample_63_important = sample_63_important;
    }

    public int getSample_64_others_expectations() {
        return sample_64_others_expectations;
    }

    public void setSample_64_others_expectations(int sample_64_others_expectations) {
        this.sample_64_others_expectations = sample_64_others_expectations;
    }

    public int getSample_65_succeeding() {
        return sample_65_succeeding;
    }

    public void setSample_65_succeeding(int sample_65_succeeding) {
        this.sample_65_succeeding = sample_65_succeeding;
    }

    public int getSample_66_something_else() {
        return sample_66_something_else;
    }

    public void setSample_66_something_else(int sample_66_something_else) {
        this.sample_66_something_else = sample_66_something_else;
    }

    public int getSample_67_feel_good() {
        return sample_67_feel_good;
    }

    public void setSample_67_feel_good(int sample_67_feel_good) {
        this.sample_67_feel_good = sample_67_feel_good;
    }

    public int getTotal_score() {return total_score;}


    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }
}
