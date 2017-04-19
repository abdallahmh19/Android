package com.example.abdullah.bookapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    private BookAdapter bookApt;
    public String url ="https://www.googleapis.com/books/v1/volumes?q=%s&maxResults=%d" ;
    EditText title;
    String finalURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title  = (EditText)findViewById(R.id.titelEditText);
        Button search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                finalURL = String.format(url, title.getText().toString(), 5);
                BookAsyncTask con = new BookAsyncTask();
                con.execute(url);
                } else {
                    Toast.makeText(MainActivity.this,R.string.checkConn,Toast.LENGTH_LONG).show();
                }
            }
        });

        final ListView bookListView = (ListView)findViewById(R.id.bookView);
        bookApt = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(bookApt);
        if (isOnline()) {
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        }
        else {
            Toast.makeText(MainActivity.this,R.string.checkConn,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Loader <List <Book>> onCreateLoader(int id, Bundle args) {
         return new BookLoader(MainActivity.this ,finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookApt.clear();
        if (data!=null && !data.isEmpty())
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
            List<Book> result = Connection.bookData(finalURL);
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
                Toast.makeText(MainActivity.this , "There is no Book "+title.getText().toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}