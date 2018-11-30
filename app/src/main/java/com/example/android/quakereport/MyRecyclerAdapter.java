package com.example.android.quakereport;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<Eartquake> list;
    public MyRecyclerAdapter(List<Eartquake> list1) {
        list = list1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout listItemView;
        public MyViewHolder(LinearLayout view) {
            super(view);
            listItemView = view;
        }
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int integer) {
        LinearLayout listItemView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.element, parent, false);
        MyViewHolder vh = new MyViewHolder(listItemView);
        return vh;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    private int getMagnitudeColor(double mag){
        int floorInt = (int) Math.floor(mag);
        if (floorInt < 0) {
            floorInt = 0;
        }

        switch (floorInt) {
            case 0:
            case 1: {
                return R.color.magnitude1;
            }
            case 2: {
                return R.color.magnitude2;
            }
            case 3: {
                return R.color.magnitude3;
            }
            case 4: {
                return R.color.magnitude4;
            }
            case 5: {
                return R.color.magnitude5;
            }
            case 6: {
                Log.w("MyRecyclerAdapter","reached magnitude of 6 to 7");
                return R.color.magnitude6;
            }
            case 7: {
                return R.color.magnitude7;
            }
            case 8: {
                return R.color.magnitude8;
            }
            case 9: {
                return R.color.magnitude9;
            }
            default: {
                return R.color.magnitude10plus;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TextView placeView = holder.listItemView.findViewById(R.id.place);
        String wholePlace = list.get(position).getPlace();
        String[] separated = new String[2];
        String DELIMITER = " of ";
        if (wholePlace.contains(DELIMITER)) {
            separated = wholePlace.split(DELIMITER);
            separated[0] = separated[0] + DELIMITER;
            separated[0] = separated[0].trim();
            separated[1] = separated[1].trim();
        } else {
            separated[0] = "Near the";
            separated[1] = wholePlace;
        }
        placeView.setText(separated[1]);
        TextView offsetView = holder.listItemView.findViewById(R.id.offset);
        offsetView.setText(separated[0]);
        TextView dateView = holder.listItemView.findViewById(R.id.date);
        Date dateTime = new Date(list.get(position).getmDate());
        DateFormat formatDate = SimpleDateFormat.getDateInstance();
        dateView.setText(formatDate.format(dateTime));
        TextView timeView = holder.listItemView.findViewById(R.id.time);
        DateFormat formatTime = SimpleDateFormat.getTimeInstance();
        timeView.setText(formatTime.format(dateTime));
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        TextView magnitudeView = holder.listItemView.findViewById(R.id.magnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        double magnitude = list.get(position).getMagnitude();
        //int magnitudeColor = getMagnitudeColor(magnitude);
        // Set the color on the magnitude circle
        //magnitudeCircle.setColor(getMagnitudeColor(magnitude));
        magnitudeCircle.setColor(magnitudeView.getResources().getColor(getMagnitudeColor(magnitude),null));
        magnitudeView.setText(decimalFormat.format(magnitude));

        holder.listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Making an intent
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(list.get(position).getUrl()));
                // Checking if device capable of handling generated intent
                PackageManager packageManager = v.getContext().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                if (isIntentSafe) {
                    // Actually sending Intent
                    v.getContext().startActivity(i);
                }
            }
        });
    }
}
