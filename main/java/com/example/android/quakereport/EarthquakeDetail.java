package com.example.android.quakereport;

public class EarthquakeDetail {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    public EarthquakeDetail(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getUrl() {
        return mUrl;
    }
}
