package com.example.abdullah.musicalsturctureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PlayListActivity extends AppCompatActivity {

    Button Store ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        String[] plays = new String[10];
        for (int i = 0 ; i<10 ; i++) {
            plays[i]="Music name : Music"+i+" \nArtist name : Artist"+i;
        }
        ListView Plays;
        ListAdapter Adp = new ArrayAdapter<String>(this,R.layout.layout,R.id.DesignPlays,plays);
        Plays =(ListView)findViewById(R.id.PlayList);
        Plays.setAdapter(Adp);

        Store = (Button)findViewById(R.id.Store);
        Store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(PlayListActivity.this , StoreActivity.class);
                startActivity(intent);

            }
        });
    }
}
