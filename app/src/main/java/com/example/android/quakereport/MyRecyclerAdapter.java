package com.example.android.quakereport;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
        placeView.setText(list.get(position).getPlace());
        TextView dateView = holder.listItemView.findViewById(R.id.date);
        dateView.setText(list.get(position).getmDate());
        TextView magnitudeView = holder.listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(list.get(position).getMagnitude());

    }
}
