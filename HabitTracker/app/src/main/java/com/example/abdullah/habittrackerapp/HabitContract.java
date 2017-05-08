package com.example.abdullah.habittrackerapp;

import android.provider.BaseColumns;

/**
 * Created by Abdullah on 5/4/2017.
 */

class HabitsContract {

    public static final class HabitsEntry implements BaseColumns {

        public static final String HABITS_TABLE_NAME = "habit";
        public static final String habitName = "name";
        public static final String habitId = "_Id";
        public static final String habitDateDone = "date_done";
    }
}