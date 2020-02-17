package com.example.team3_todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todosAdapter extends RecyclerView.Adapter<todosAdapter.ToDoViewHolder> {

    Context context;
    ArrayList<todos> todos;

    public todosAdapter(Context c, ArrayList<todos> p) {
        context = c;
        todos = p;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToDoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        holder.title.setText(todos.get(position).getTitle());
        holder.description.setText(todos.get(position).getDescription());
        holder.date.setText(todos.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, date;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }
    }

}
