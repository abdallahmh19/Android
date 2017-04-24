package com.example.abdullah.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdullah on 4/15/2017.
 */

public class Connection {

    public static final String LOG_TAG = Connection.class.getSimpleName();

    public Connection() {
    }

    public static List<Story> storyData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try
        {
            jsonResponse = makehttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"There is an error in the request",e);
        }
        List<Story> Stories = parseStories(jsonResponse);
        return Stories;
    }
    private static URL createUrl(String Url){
        URL url = null;
        try
        {
            url = new URL(Url);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error in Creating URL",e);
        }
        return url;
    }

    private static String makehttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000 );
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error in the connection ");
            }

        }catch (IOException e){
            Log.e(LOG_TAG, "Error in JSON books", e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;

    }
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static List<Story> parseStories(String bookJSON){
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }
        ArrayList<Story> stories = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray storyList = response.getJSONArray("results");

            for (int i = 0; i < storyList.length(); i++) {

                Story story = new Story();
                JSONObject item = storyList.getJSONObject(i);

                // JSONObject volumeInfo = item.getJSONObject("results");

                String sectionName = item.getString("sectionName");
                story.setSectionName(sectionName);

                if (item.has("webPublicationDate")){
                    String publishDate = item.getString("webPublicationDate");
                    story.setPublishDate(publishDate);
                }
                else {
                    story.setPublishDate("UnKnown Date");
                }

                String title = item.getString("webTitle");
                story.setTitle(title);

                String url = item.getString("webUrl");
                story.setWebUrl(url);

                stories.add(story);
            }
            } catch (JSONException e1) {
            Log.e (e1.toString() , "There is an Exception in json parse");
        }

        return stories;
    }

}