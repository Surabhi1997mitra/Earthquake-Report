package com.example.android.quakereport;

public class EarthquakeDetail {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

    public EarthquakeDetail(String magnitude, String Location, String date)
    {
        this.mMagnitude = magnitude;
        this.mLocation = Location;
        this.mDate = date;
    }


    public String getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(String mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
