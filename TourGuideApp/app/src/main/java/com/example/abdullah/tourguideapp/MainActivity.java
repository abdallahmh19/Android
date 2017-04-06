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

            cities.add(new Word (R.string.city1_name,R.string.city1_location ,R.drawable.makkah));
            cities.add(new Word (R.string.city2_name,R.string.city2_location,R.drawable.dubai));
            cities.add(new Word (R.string.city3_name,R.string.city3_location ,R.drawable.doha));
            cities.add(new Word (R.string.city4_name,R.string.city4_location,R.drawable.riyadh));
            cities.add(new Word (R.string.city5_name,R.string.city5_location ,R.drawable.london));
            cities.add(new Word (R.string.city6_name,R.string.city6_location,R.drawable.washington));
            cities.add(new Word (R.string.city7_name,R.string.city7_location ,R.drawable.berlin));

            WordAdapter citiesAdapter= new WordAdapter(this, cities, R.color.cities_colors);
            content.setAdapter(citiesAdapter);
        }

        else if (id== R.id.restaurants){
        final ArrayList<Word> rest = new ArrayList<Word>();
            rest.add(new Word(R.string.rest1_name ,R.string.rest1_location,R.drawable.theglobe));
            rest.add(new Word(R.string.rest2_name ,R.string.rest2_location,R.drawable.spazio));
            rest.add(new Word(R.string.rest3_name,R.string.rest3_location,R.drawable.cheesecakefactory));
            rest.add(new Word(R.string.rest4_name ,R.string.rest4_location,R.drawable.socialhouse));
            WordAdapter restsAdapter= new WordAdapter(this, rest, R.color.restaurants_color);
            content.setAdapter(restsAdapter);
        }
        else if (id == R.id.events){
        final ArrayList<Word> events = new ArrayList<Word>();
            events.add(new Word (R.string.event1_name,R.string.event1_location,R.drawable.event0));
            events.add(new Word (R.string.event2_name,R.string.event2_location,R.drawable.event1));
            events.add(new Word (R.string.event3_name,R.string.event3_location,R.drawable.event2));
            events.add(new Word (R.string.event4_name,R.string.event4_location));
            events.add(new Word (R.string.event5_name,R.string.event5_location,R.drawable.event4));
            events.add(new Word (R.string.event6_name,R.string.event6_location,R.drawable.event5));
            WordAdapter eventsAdapter= new WordAdapter(this, events, R.color.events_color);
            content.setAdapter(eventsAdapter);
        }
        else if (id==R.id.places){
            final ArrayList<Word> places = new ArrayList<Word>();
            places.add(new Word(R.string.place1_name,R.string.place1_location,R.drawable.salih));
            places.add(new Word(R.string.place2_name,R.string.place2_location,R.drawable.farasan));
            places.add(new Word(R.string.place3_name,R.string.place3_location,R.drawable.bujiri));
            places.add(new Word(R.string.place4_name,R.string.place4_location,R.drawable.souq));
            places.add(new Word(R.string.place5_name,R.string.place5_location,R.drawable.burj));
            places.add(new Word(R.string.place6_name,R.string.place6_location,R.drawable.gv));
            places.add(new Word(R.string.place7_name,R.string.place7_location,R.drawable.aquaventurewaterpark));

            WordAdapter placesAdapter= new WordAdapter(this, places, R.color.places_color);
            content.setAdapter(placesAdapter);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
