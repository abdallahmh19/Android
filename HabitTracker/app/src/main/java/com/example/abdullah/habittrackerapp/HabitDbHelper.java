package com.example.abdullah.habittrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abdullah on 5/4/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {


        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "habit.db";
        private SQLiteDatabase db;

        public HabitDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDB) {
            Log.i("onCreate", "true");
            final String SQL_CREATE_HABITS_TABLE =
                    "CREATE TABLE IF NOT EXISTS " +
                            HabitsContract.HabitsEntry.HABITS_TABLE_NAME + " (" +
                            HabitsContract.HabitsEntry.habitId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            HabitsContract.HabitsEntry.habitName + " TEXT NOT NULL, " +
                            HabitsContract.HabitsEntry.habitDateDone + " TEXT NOT NULL " +
                            ")";
            sqLiteDB.execSQL(SQL_CREATE_HABITS_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitsContract.HabitsEntry.HABITS_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }


        public void insertRecord(ContentValues contentValues) {
            db = getWritableDatabase();
            try {
                db.insert(HabitsContract.HabitsEntry.HABITS_TABLE_NAME, null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }


        public void updateRecord(int recordId, ContentValues contentValues) {
            db = getWritableDatabase();
            try {
                db.update(
                        HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                        contentValues,
                        HabitsContract.HabitsEntry.habitId + "=" + recordId,
                        null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
    public Cursor getRecord(int recordId) {
        Cursor record;
        String table = HabitsContract.HabitsEntry.HABITS_TABLE_NAME;
        String selection = HabitsContract.HabitsEntry.habitId + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(recordId)};
        db = getReadableDatabase();
        try {
            record = db.query(true, table, null, selection, selectionArgs, null, null, null, null);
            record.moveToFirst();
            record.close();
            db.close();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }
    }
}
