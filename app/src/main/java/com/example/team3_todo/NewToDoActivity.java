package com.example.team3_todo;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Random;

public class NewToDoActivity extends AppCompatActivity {

    TextView titlepage, addtitle, adddescription, adddate;
    EditText title, description, date;
    Date label;
    DatePicker picker;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        final Integer todoID = new Random().nextInt();
        final String key = Integer.toString(todoID);

        titlepage = findViewById(R.id.titlepage);

        addtitle = findViewById(R.id.addtitle);
        adddescription = findViewById(R.id.adddescription);
        adddate = findViewById(R.id.adddate);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        picker = findViewById(R.id.datePicker);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("Team3ToDo").
                        child("ToDo" + todoID);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("title").setValue(title.getText().toString());
                        dataSnapshot.getRef().child("description").setValue(description.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(picker.getDayOfMonth() + ", " + (picker.getMonth() + 1) + ", " + picker.getYear());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();

                        Intent a = new Intent(NewToDoActivity.this, MainActivity.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(NewToDoActivity.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}
