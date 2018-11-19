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


import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EarthquakeActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Eartquake> earthquakes = new ArrayList<>();

        Date date = Calendar.getInstance().getTime();
        earthquakes.add(new Eartquake(7.2f,"San Francisco", date));
        earthquakes.add(new Eartquake(2.8f,"London", date));
        earthquakes.add(new Eartquake(6.1f,"Tokyo", date));
        earthquakes.add(new Eartquake(3.9f,"Mexico City", date));
        earthquakes.add(new Eartquake(5.4f,"Moscow", date));
        earthquakes.add(new Eartquake(4.9f,"Rio de Janeiro", date));
        earthquakes.add(new Eartquake(1.6f,"Paris", date));

        // Find a reference to the {@link ListView} in the layout
        RecyclerView earthquakeRecyclerView =  findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        earthquakeRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(earthquakeRecyclerView.getContext(),
                LinearLayout.VERTICAL);
        earthquakeRecyclerView.addItemDecoration(dividerItemDecoration);

        // Create a new {@link ArrayAdapter} of earthquakes
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeRecyclerView.setAdapter(adapter);
    }
}
