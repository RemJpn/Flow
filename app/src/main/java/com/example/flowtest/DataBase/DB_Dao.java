package com.example.flowtest.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DB_Dao {

    @Insert
    public void addSample(DB_Entity sample);
    @Update
    public void updateUsers(DB_Entity sample);

    @Query("SELECT * from samples")
    public List<DB_Entity> getSamples();
    @Query("SELECT * FROM samples WHERE survey_num = :result_survey_id")
    public List<DB_Entity> getSamplesFromSurveyId(int result_survey_id);

    @Query("SELECT * FROM samples WHERE survey_num = :result_survey_id ORDER BY total_score DESC")
    public List<DB_Entity> getSamplesByScore(int result_survey_id);



    @Delete
    public void deleteSample(DB_Entity sample);


}
