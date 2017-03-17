package com.example.abdullah.musicalsturctureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PlayNowActivity extends AppCompatActivity {

    Button Playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_now);

        Playlist = (Button)findViewById(R.id.Playlist);

        Playlist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){
                Intent intent = new Intent(PlayNowActivity.this , PlayListActivity.class);
                startActivity(intent);
            }

        });
    }
}
