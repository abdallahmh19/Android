package com.example.abdullah.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah on 4/24/2017.
 */

public class StoryAdapter extends ArrayAdapter<Story> {

    // it used in setStories method
    private List<Story> storyList = new ArrayList<>();

    public StoryAdapter(Activity context, ArrayList<Story> stories) {
        super(context, 0, stories);

    }
    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {

        View listItemView = view;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.story_item, viewgroup, false);
        }
        Story story_item = getItem(position);

        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.sectionName);
        sectionNameTextView.setText(story_item.getSectionName());

        TextView publishDateTextView = (TextView) listItemView.findViewById(R.id.publishDate);
        publishDateTextView.setText(story_item.getPublishDate());

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.webTitle);
        titleTextView.setText(story_item.getTitle());

        return listItemView;
    }

    public void  setStories(List<Story> stories){
        storyList = stories;
        notifyDataSetChanged();
    }

}
