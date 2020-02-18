package com.example.team3_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, copyright;

    DatabaseReference reference;
    RecyclerView todos;
    ArrayList<todos> list;
    todosAdapter todosAdapter;

    final int SECOND_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage = findViewById(R.id.titlepage);
        copyright = findViewById(R.id.copyright);

        // Data
        todos = findViewById(R.id.todos);
        todos.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<todos>();

        // Manually add data
        Button button = findViewById(R.id.btnAddNew);
        button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), AddNewActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY);
            }
        });

            // Retrieve data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Team3ToDo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                todos todo = (todos)data.getSerializableExtra("todo");
                writeNewTodo(todo);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void writeNewTodo(todos Todo) {
        Integer todoID = new Random().nextInt();
        String keydoes = Integer.toString(todoID);
        todos todo = Todo;
        if(todo != null) {
            reference.child("ToDo" + todoID).child("title").setValue(todo.getTitle());
            reference.child("ToDo" + todoID).child("description").setValue(todo.getDescription());
            reference.child("ToDo" + todoID).child("date").setValue(todo.getData());
        }
    }

}
