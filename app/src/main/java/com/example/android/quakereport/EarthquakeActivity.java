/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();


    // Create a fake list of earthquake locations.


    //liveDataEarthquakes = new JsonViewModel(getApplication()).getData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        TextView textview = findViewById(R.id.empty_view);
        textview.setVisibility(View.GONE);
        View progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView earthquakeRecyclerView =  findViewById(R.id.list);
        earthquakeRecyclerView.setVisibility(View.GONE);
        //Check network state
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            earthquakeRecyclerView.setLayoutManager(mLayoutManager);
            JsonViewModel model = ViewModelProviders.of(this).get(JsonViewModel.class);
            //Log.w(LOG_TAG,"Started observing");
            model.getData().observe(this, liveDataEarthquakes -> {
                MyRecyclerAdapter adapter = new MyRecyclerAdapter(liveDataEarthquakes);
                earthquakeRecyclerView.setAdapter(adapter);
                textview.setText("No data loaded");
                progressBar.setVisibility(View.GONE);
                if (liveDataEarthquakes.isEmpty()) {
                    textview.setVisibility(View.VISIBLE);
                    earthquakeRecyclerView.setVisibility(View.GONE);
                } else {
                    textview.setVisibility(View.GONE);
                    earthquakeRecyclerView.setVisibility(View.VISIBLE);
                }
            });
        } else {
            // notify user you are not online
            textview.setVisibility(View.VISIBLE);
            textview.setText("No internet conncetion");
            earthquakeRecyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }




        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

        /*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(earthquakeRecyclerView.getContext(),
                LinearLayout.VERTICAL);
        earthquakeRecyclerView.addItemDecoration(dividerItemDecoration);
        */
        // Create a new {@link ArrayAdapter} of earthquakes




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
