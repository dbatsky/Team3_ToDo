package com.example.team3_todo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class todosAdapter extends RecyclerView.Adapter<todosAdapter.ToDoViewHolder> {

    Context context;
    ArrayList<todos> todos;

    Date label;

    public todosAdapter(Context c, ArrayList<todos> p) {
        context = c;
        todos = p;
        for (int position = 0; position < todos.size(); position++) {
            DateFormat formatter = new SimpleDateFormat("dd, MM, yyyy", Locale.US);
            try {
                label = formatter.parse(todos.get(position).getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            todos.get(position).setLabel(label);
        }

        Comparator<todos> compareByDate = new Comparator<todos>() {
            @Override
            public int compare(todos o1, todos o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        };

        Collections.sort(todos, compareByDate);
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
        DateFormat formatter = new SimpleDateFormat("MMM, dd", Locale.US);
        holder.date.setText(formatter.format(todos.get(position).getLabel()));


        final String getTitle = todos.get(position).getTitle();
        final String getDescription = todos.get(position).getDescription();
        final String getDate = todos.get(position).getDate();
        final String getKey = todos.get(position).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditToDoActivity.class);
                i.putExtra("title", getTitle);
                i.putExtra("description", getDescription);
                i.putExtra("date", getDate);
                i.putExtra("key", getKey);
                context.startActivity(i);
            }
        });
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
