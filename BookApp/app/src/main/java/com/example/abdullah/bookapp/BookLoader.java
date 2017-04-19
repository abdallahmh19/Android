package com.example.abdullah.bookapp;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Abdullah on 4/19/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    String url;

    public BookLoader(Context context , String url) {
        super(context);
        this.url=url;
    }
    @Override
    public List<Book> loadInBackground() {
        List<Book> books = Connection.bookData(url);
        return books;
    }

}
