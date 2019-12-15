package com.example.rxjavamemoryleak;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ellison
 * @date 2019/12/15
 * @desc 用一句话描述这个类的作用
 */
public class CurrentListAdapter extends RecyclerView.Adapter<CurrentListAdapter.CurrentHolder> {

    List<String> data;

    public CurrentListAdapter(List<String> data) {
        this.data = data == null ? new ArrayList<>() : data;
    }

    @NonNull
    @Override
    public CurrentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CurrentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addDatas(List<String> listDatas) {
        data.addAll(listDatas);
        notifyDataSetChanged();
    }

    class CurrentHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public CurrentHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
