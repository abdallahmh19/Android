package com.example.abdullah.tourguideapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        colorID = colorResourceId;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {

        View listItemView = view;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, viewgroup, false);
        }
        Word word_item = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(word_item.getNameID());

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(word_item.getLocationID());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(word_item.hasImage()) {

            imageView.setImageResource(word_item.getImageID());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setImageResource(R.drawable.noimage);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        textContainer.setBackgroundResource(colorID);

        return listItemView;
    }
}
