package com.RS.flowtest.DataBase;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.RS.flowtest.MainActivity;

import java.util.List;

import static com.RS.flowtest.SampleActivity.sampleDatabase;

@Database(entities = {DB_Entity.class},version = 2,exportSchema = false)
public abstract class SampleDatabase extends RoomDatabase {

    private static SampleDatabase INSTANCE;
    public abstract DB_Dao myDao();
    private static final String DB_NAME = "sampledb";


    /**
     * Migrate from:
     * version 1 - initial DB
     * to
     * version 2 - extra field: total_score
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE samples "
                    + " ADD COLUMN total_score INTEGER NOT NULL DEFAULT 0");

            if(MainActivity.DEV_MODE)
                Log.d("Flow_etapes","Migration");

            //initialize values of total_score
            List<DB_Entity> samples =  sampleDatabase.myDao().getSamples();
            for(DB_Entity spl : samples) {
                int total_score = spl.getSample_56_enjoy()
                        + spl.getSample_57_interesting()
                        + spl.getSample_58_concentrating()
                        + spl.getSample_59_expectations()
                        + spl.getSample_60_control()
                        + spl.getSample_61_involved()
                        + spl.getSample_62_deal()
                        + spl.getSample_63_important()
                        + spl.getSample_64_others_expectations()
                        + spl.getSample_65_succeeding()
                        - spl.getSample_66_something_else()
                        + spl.getSample_67_feel_good();
                spl.setTotal_score(total_score);
                sampleDatabase.myDao().updateUsers(spl);
            }
        }
    };

    public static SampleDatabase getInstance(Context context) {
        synchronized (SampleDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        SampleDatabase.class, DB_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }


}
