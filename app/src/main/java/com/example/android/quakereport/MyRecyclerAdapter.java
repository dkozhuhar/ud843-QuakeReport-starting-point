package com.example.android.quakereport;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private ArrayList<Eartquake> list;
    public MyRecyclerAdapter(ArrayList<Eartquake> list1) {
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView placeView = holder.listItemView.findViewById(R.id.place);
        String wholePlace = list.get(position).getPlace();
        String[] separated = new String[2];
        if (wholePlace.contains("of")) {
            separated = wholePlace.split("of");
            separated[0] = separated[0] + "of";
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
        TextView magnitudeView = holder.listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(list.get(position).getMagnitude());

    }
}
