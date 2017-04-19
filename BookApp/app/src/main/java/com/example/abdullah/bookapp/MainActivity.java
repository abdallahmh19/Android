package com.example.abdullah.bookapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private String Book_URL = " https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    private BookAdapter bookApt;
    public String url =" https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5" ;
    EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title  = (EditText)findViewById(R.id.titelEditText);
        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url =Book_URL.replaceAll("android",title.getText().toString());
                BookAsyncTask con = new BookAsyncTask();
                con.execute(url);
            }
        });

        final ListView bookListView = (ListView)findViewById(R.id.bookView);
        bookApt = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(bookApt);
        getSupportLoaderManager().initLoader(1, null,this).forceLoad();
    }

    @Override
    public Loader <List <Book>> onCreateLoader(int id, Bundle args) {
         return new BookLoader(MainActivity.this ,url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookApt.clear();
        bookApt.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookApt.clear();
        bookApt.setBooks(new ArrayList<Book>());
    }


    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        private ProgressDialog progressDialog;

        @Override
        protected List<Book> doInBackground(String... strings) {
            List<Book> result = Connection.bookData(url);
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("searching...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Book> data) {
            bookApt.clear();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (data != null && !data.isEmpty()) {
                bookApt.addAll(data);
            }
            else {
                Toast.makeText(MainActivity.this ,"There is no books name "+title.getText().toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

}