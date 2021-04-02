package io.github.kwanyoon.androidapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.kwanyoon.androidapplication.MainActivity;
import io.github.kwanyoon.androidapplication.R;
import io.github.kwanyoon.androidapplication.model.ToDoModel;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<ToDoModel> todoList;
    private MainActivity activity;

    public ToDoAdapter(MainActivity activity) {
        this.activity = activity;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new RecyclerView.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        
    }
}
