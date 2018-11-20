package com.example.android.quakereport;

import java.util.Date;

public class Eartquake {
    private String magnitude;
    private String place;
    private long mDate;
    private void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
    private void setmDate(long mDate) {
        this.mDate = mDate;
    }
    private void setPlace(String place) {
        this.place = place;
    }

    public long getmDate() {
        return mDate;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }
    public Eartquake(String mag, String placee, long date){
        setMagnitude(mag);
        setmDate(date);
        setPlace(placee);
    }
}
