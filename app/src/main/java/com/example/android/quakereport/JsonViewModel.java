package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonViewModel extends AndroidViewModel {
    private final JsonLiveData data;
    public JsonViewModel(Application application) {
        super(application);
        data = new JsonLiveData(application);
    }
    public LiveData<List<Eartquake>> getData() {
        if (data == null) {Log.e("JsonViewModel","No data loaded"); }
        return data;
    }

}
