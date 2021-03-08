package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EarthquakeDetailAdapter extends ArrayAdapter<EarthquakeDetail> {

    private static final String LOCATION_SEPARATOR = " of ";

    //customAdapter Cunstructor
    public EarthquakeDetailAdapter(Context context, ArrayList<EarthquakeDetail> earthquakeDetails) {
        super(context, 0, earthquakeDetails);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        EarthquakeDetail currentEarthquakeDetail = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        //String formattedMagnitude = formatMagnitude(currentEarthquakeDetail.getMagnitude());
        magnitudeView.setText(currentEarthquakeDetail.getMagnitude());

//        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeView.getBackground();
//        int magnitudeColor = getMagnitudeColor(currentEarthquakeDetail.getMagnitude());
//        magnitudeCircle.setColor(magnitudeColor);

//        String originalLocation = currentEarthquakeDetail.getLocation();
//        String primaryLocation,locationOffset;
//        if(originalLocation.contains(LOCATION_SEPARATOR))
//        {
//            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
//            locationOffset = parts[0] + LOCATION_SEPARATOR;
//            primaryLocation = parts[1];
//        }
//        else
//        {
//            locationOffset = getContext().getString(R.string.near_the);
//            primaryLocation = originalLocation;
//        }

        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        locationView.setText(currentEarthquakeDetail.getLocation());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(currentEarthquakeDetail.getDate());

        return listItemView;
    }
}
