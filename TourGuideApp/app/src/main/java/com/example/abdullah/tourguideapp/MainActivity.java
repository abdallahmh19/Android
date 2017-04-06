package com.example.abdullah.tourguideapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (ListView)findViewById(R.id.info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cities) {
        final ArrayList<Word> cities = new ArrayList<Word>();

            cities.add(new Word ("Makkah","Kingdom of Saudi Arabia" ,R.drawable.makkah));
            cities.add(new Word ("Dubai","United Arab Emirates" ,R.drawable.dubai));
            cities.add(new Word ("Doha","Qatar" ,R.drawable.doha));
            cities.add(new Word ("Riyadh","Kingdom of Saudi Arabia" ,R.drawable.riyadh));
            cities.add(new Word ("London","United Kingdom" ,R.drawable.london));
            cities.add(new Word ("Washington","United States" ,R.drawable.washington));
            cities.add(new Word ("Berlin","Germany" ,R.drawable.berlin));

            WordAdapter citiesAdapter= new WordAdapter(this, cities, R.color.cities_colors);
            content.setAdapter(citiesAdapter);
        }
        else if (id== R.id.restaurants){
        final ArrayList<Word> rest = new ArrayList<Word>();
            rest.add(new Word("The Globe" ,"Al Faisaliah Tower , Riyadh KSA",R.drawable.theglobe));
            rest.add(new Word("Spazio" ,"Al Mamlakah Tower , Riyadh KSA",R.drawable.spazio));
            rest.add(new Word("The Cheesecake Factory" ,"Dubai Mall , Dubai UAE",R.drawable.cheesecakefactory));
            rest.add(new Word("Social House" ,"Dubai Mall , Dubai UAE",R.drawable.socialhouse));
            WordAdapter restsAdapter= new WordAdapter(this, rest, R.color.restaurants_color);
            content.setAdapter(restsAdapter);
        }
        else if (id == R.id.events){
        final ArrayList<Word> events = new ArrayList<Word>();
            events.add(new Word ("Misk Art from 4-8 April","Banban , Riyadh",R.drawable.event0));
            events.add(new Word ("Event 2","Ritz-Carilton, Riyadh",R.drawable.event1));
            events.add(new Word ("PMP Training","Ritz-Carilton , Riyadh",R.drawable.event2));
            events.add(new Word ("Art","Olaya , Riyadh"));
            events.add(new Word ("Management","Olaya , Riyadh",R.drawable.event4));
            events.add(new Word ("Drawing","Olaya , Riyadh",R.drawable.event5));
            WordAdapter eventsAdapter= new WordAdapter(this, events, R.color.events_color);
            content.setAdapter(eventsAdapter);
        }
        else if (id==R.id.places){
            final ArrayList<Word> places = new ArrayList<Word>();
            places.add(new Word("Madain Saleh","Al Ula, KSA",R.drawable.salih));
            places.add(new Word("Farasan Island", "Jazan, KSA",R.drawable.farasan));
            places.add(new Word("Al Bujairi square", "Riyadh, KSA",R.drawable.bujiri));
            places.add(new Word("Souq Waqif","Doha, Qatar",R.drawable.souq));
            places.add(new Word("Burj Khalifa","Dubai, UAE",R.drawable.burj));
            places.add(new Word ("Global Village","Dubai, UAE",R.drawable.gv));
            places.add(new Word("Aquaventure Waterpark","Dubai, UAE",R.drawable.aquaventurewaterpark));

            WordAdapter placesAdapter= new WordAdapter(this, places, R.color.places_color);
            content.setAdapter(placesAdapter);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
