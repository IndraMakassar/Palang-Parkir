package com.example.easyparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<User2> list;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User2 item);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    public MyAdapter(Context context, ArrayList<User2> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User2 user = list.get(position);
        holder.mall.setText(user.getNamaMall());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mall = itemView.findViewById(R.id.listMall);
        }
    }
}
