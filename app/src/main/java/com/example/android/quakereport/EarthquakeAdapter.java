package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asa on 2018/4/6.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    //constructor
    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.color.magnitude1);
        colors.add(R.color.magnitude2);
        colors.add(R.color.magnitude3);
        colors.add(R.color.magnitude4);
        colors.add(R.color.magnitude5);
        colors.add(R.color.magnitude6);
        colors.add(R.color.magnitude7);
        colors.add(R.color.magnitude8);
        colors.add(R.color.magnitude9);
        colors.add(R.color.magnitude10plus);
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Get current item in the list
        Earthquake earthquakeCurrent = getItem(position);

        //Update UI
        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(earthquakeCurrent.getmMignatude());
        textViewMagnitude.setText(formattedMagnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) textViewMagnitude.getBackground();
        int magnitudeC = (int) Math.floor(earthquakeCurrent.getmMignatude());
        int magnitudeColor;
        if (magnitudeC < 2) {
            magnitudeColor = colors.get(0);
        } else if (magnitudeC < 10) {
            magnitudeColor = colors.get(magnitudeC - 1);
        } else magnitudeColor = colors.get(9);
        magnitudeCircle.setColor(ContextCompat.getColor(getContext(), magnitudeColor));

        //deal with char of place
        String locationAll = earthquakeCurrent.getmPlace();
        String near;
        String placeGeneral;
        if (locationAll.contains("of ")) {
            near = locationAll.substring(0, locationAll.indexOf("of ") + 3);
            placeGeneral = locationAll.substring(locationAll.indexOf("of ") + 3);
        } else {
            near = "Near the";
            placeGeneral = locationAll;
        }

        TextView textViewNear = (TextView) listItemView.findViewById(R.id.near_the);
        textViewNear.setText(near);
        TextView textViewPlace = (TextView) listItemView.findViewById(R.id.place);
        textViewPlace.setText(placeGeneral);

        Date dateObject = new Date(earthquakeCurrent.getmTime());

        TextView textViewDate = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        textViewDate.setText(formattedDate);

        TextView textViewTime = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        textViewTime.setText(formattedTime);


        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


}
