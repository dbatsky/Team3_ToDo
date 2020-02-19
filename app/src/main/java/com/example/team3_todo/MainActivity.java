package com.example.team3_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, copyright;
    Button btnAddNew;

    DatabaseReference reference;
    RecyclerView todos;
    ArrayList<todos> list;
    todosAdapter todosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        copyright = findViewById(R.id.copyright);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer getKey = todosAdapter.getItemCount();
                Intent a = new Intent(MainActivity.this, NewToDoActivity.class);
                a.putExtra("key", getKey);
                startActivity(a);
            }
        });

        // Data
        todos = findViewById(R.id.todos);
        todos.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<todos>();


        // Retrieve data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Team3ToDo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    todos p = dataSnapshot1.getValue(todos.class);
                    list.add(p);
                }
                todosAdapter = new todosAdapter(MainActivity.this, list);
                todos.setAdapter(todosAdapter);
                todosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
