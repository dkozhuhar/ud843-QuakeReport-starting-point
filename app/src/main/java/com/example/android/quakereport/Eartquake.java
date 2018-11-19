package com.example.android.quakereport;

import java.util.Date;

public class Eartquake {
    private float magnitude;
    private String place;
    private Date mDate;
    private void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }
    private void setmDate(Date mDate) {
        this.mDate = mDate;
    }
    private void setPlace(String place) {
        this.place = place;
    }

    public String getmDate() {
        return mDate.toString();
    }

    public String getMagnitude() {
        return Float.toString(magnitude);
    }

    public String getPlace() {
        return place;
    }
    public Eartquake(float mag, String placee, Date date){
        setMagnitude(mag);
        setmDate(date);

        setPlace(placee);
    }
}
