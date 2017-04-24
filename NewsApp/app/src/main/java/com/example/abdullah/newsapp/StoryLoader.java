package com.example.abdullah.newsapp;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;


import java.util.List;

/**
 * Created by Abdullah on 4/19/2017.
 */

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    String url;

    public StoryLoader(Context context , String url) {
        super(context);
        System.out.println("Loader URL: "+url);
        this.url=url;
    }
    @Override
    public List<Story> loadInBackground() {
        List<Story> stories = Connection.storyData(url);
        return stories;
    }

}
