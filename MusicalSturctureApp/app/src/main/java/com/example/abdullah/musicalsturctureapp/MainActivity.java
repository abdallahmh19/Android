package com.example.abdullah.musicalsturctureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button NowPlaying ,Playlist , Store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NowPlaying = (Button)findViewById(R.id.NowPlaying);
        NowPlaying.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , PlayNowActivity.class);
                startActivity(intent);
            }
        });

        Playlist = (Button)findViewById(R.id.Playlist);
        Playlist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , PlayListActivity.class);
                startActivity(intent);
            }
        });

        Store = (Button)findViewById(R.id.Srore);
        Store.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , StoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
