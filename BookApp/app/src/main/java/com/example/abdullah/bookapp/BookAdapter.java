package com.example.abdullah.bookapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter  extends ArrayAdapter<Book> {

    // it used in setBooks method
    private List<Book> bookList = new ArrayList<>();

    public BookAdapter(Activity context, ArrayList<Book> Books) {
        super(context, 0, Books);

    }
    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {

        View listItemView = view;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, viewgroup, false);
        }
        Book book_item = getItem(position);

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        authorTextView.setText(book_item.getAuthor());

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(book_item.getTitle());

        return listItemView;
    }

    public void setBooks (List<Book> books){
        bookList = books;
        notifyDataSetChanged();
    }

}
