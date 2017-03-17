package com.example.abdullah.musicalsturctureapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        String[] plays = new String[10];
        for (int i = 0 ; i<10 ; i++) {
            plays[i]="Music name : Music"+i+"\nArtist name : Artist"+i+"\nPrices : $"+(i*1.25)+"";
        }
        ListView Plays;
        ListAdapter Adp = new ArrayAdapter<String>(this,R.layout.layout,R.id.DesignPlays,plays);
        Plays =(ListView)findViewById(R.id.Storelist);
        Plays.setAdapter(Adp);
    }
}
