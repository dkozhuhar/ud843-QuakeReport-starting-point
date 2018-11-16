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
    private ArrayList<String> list;
    public MyRecyclerAdapter(ArrayList<String> list1) {
        list = list1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView listItemView;
        public MyViewHolder(TextView view) {
            super(view);
            listItemView = view;
        }
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int integer) {
        TextView listItemView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        MyViewHolder vh = new MyViewHolder(listItemView);
        return vh;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textView = holder.listItemView.findViewById(android.R.id.text1);
        textView.setText(list.get(position));
    }
}
