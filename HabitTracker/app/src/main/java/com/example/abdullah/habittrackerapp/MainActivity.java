package com.example.abdullah.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper habitsDBHelper = new HabitDbHelper(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitsContract.HabitsEntry.habitName, "Playing PS");
        contentValues.put(HabitsContract.HabitsEntry.habitDateDone, "5/4/2017");
        habitsDBHelper.insertRecord(contentValues);


        contentValues.put(HabitsContract.HabitsEntry.habitName, "Football");
        habitsDBHelper.updateRecord(1, contentValues);


        Cursor selectHabit = habitsDBHelper.getRecord(1);
        Log.i("selectHabit" ,selectHabit.toString());
    }
}
