package com.example.abdullah.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Story>> {


    public String url= "https://content.guardianapis.com/search?q=%s&api-key=bcfa439b-4c48-487f-8598-0cecdb58b931" ;
    private StoryAdapter storyAdapter;
    EditText title;
    String finalURL = "";
    TextView emptyList;
    ListView bookListView;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyList=(TextView)findViewById(R.id.empty_list_view);
        title  = (EditText)findViewById(R.id.titelEditText);
        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalURL=String.format(url,title.getText().toString());
                if (isOnline()) {


                    new StoryLoader(MainActivity.this ,finalURL);
                    getSupportLoaderManager().restartLoader(1, null, MainActivity.this).forceLoad();


                } else {
                    Toast.makeText(MainActivity.this,R.string.checkConn,Toast.LENGTH_LONG).show();
                }
            }
        });

        bookListView = (ListView)findViewById(R.id.newsView);
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Story story = storyAdapter.getItem(i);
                Uri earthquakeUri = Uri.parse(story.getWebUrl());
                Intent webUrl = new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(webUrl);
            }
        });

        storyAdapter = new StoryAdapter(this, new ArrayList<Story>());
        bookListView.setAdapter(storyAdapter);
        if (isOnline()) {
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        }
        else {
            Toast.makeText(MainActivity.this,R.string.checkConn,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public Loader<List<Story>> onCreateLoader(int id, Bundle args) {
        return new StoryLoader(MainActivity.this ,finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> data) {

        storyAdapter.clear();
        if (data!=null && ! data.isEmpty()) {
            emptyList.setVisibility(View.INVISIBLE);
            storyAdapter.addAll(data);
            bookListView.setVisibility(View.VISIBLE);
        }
        else if(counter>0) {
            bookListView.setVisibility(View.INVISIBLE);
            emptyList.setVisibility(View.VISIBLE);
            emptyList.setText(R.string.noBook);
        }else{
            bookListView.setVisibility(View.INVISIBLE);
            emptyList.setVisibility(View.VISIBLE);
            emptyList.setText(R.string.emptyListTextView);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {
        storyAdapter.clear();
        storyAdapter.setStories(new ArrayList<Story>());
    }
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
