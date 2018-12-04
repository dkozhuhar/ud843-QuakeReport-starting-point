package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class JsonLiveData extends LiveData<List<Eartquake>> {
    private final Context context;
    public JsonLiveData(Context context) {
        this.context = context;
        loadData();
    }
    @SuppressLint("StaticFieldLeak")
    private void loadData() {
        new AsyncTask<String,Void,List<Eartquake>>() {

            @Override
            protected List<Eartquake> doInBackground(String... strings) {
                // Parse the JSON using the library of your choice
                List<Eartquake> earthquakes = QueryUtils.fetchEarthquakeData(QueryUtils.USGS_URL);
                return earthquakes;
            }
            @Override
            protected void onPostExecute(List<Eartquake> data) {
                super.onPostExecute(data);
                setValue(data);
            }
        }.execute();
    }
}
