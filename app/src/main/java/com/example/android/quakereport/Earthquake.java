package com.example.android.quakereport;

/**
 * Created by asa on 2018/4/6.
 */

public class Earthquake {

    private double mMignatude;
    private String mPlace;
    private long mTime;
    private String mURL;

    //constructor
    public Earthquake(double mignatude, String place, long time, String url) {

        mMignatude = mignatude;
        mPlace = place;
        mTime = time;
        mURL = url;
    }

    //getter fonctions
    public double getmMignatude() {
        return mMignatude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public long getmTime() {
        return mTime;
    }

    public String getmURL() {
        return mURL;
    }
}
