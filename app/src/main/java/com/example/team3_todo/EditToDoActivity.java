package com.example.team3_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditToDoActivity extends AppCompatActivity {

    EditText title, description;
    Button btnSave, btnDelete;
    DatabaseReference reference;
    Date labelDay, labelMonth, labelYear;
    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));

        picker = findViewById(R.id.datePicker);

        String[] split = getIntent().getStringExtra("date").split(", ");
        int day = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        int year = Integer.valueOf(split[2]);

        picker.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                // Notify the user.

            }
        });

        final String key = getIntent().getStringExtra("key");

        reference = FirebaseDatabase.getInstance().getReference().child("Team3ToDo").
                child("ToDo" + key);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data to database
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("title").setValue(title.getText().toString());
                        dataSnapshot.getRef().child("description").setValue(description.getText().toString());
                        dataSnapshot.getRef().child("date").setValue(picker.getDayOfMonth() + ", " + (picker.getMonth() + 1) + ", " + picker.getYear());
                        dataSnapshot.getRef().child("key").setValue(key);

                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                        Intent a = new Intent(EditToDoActivity.this, MainActivity.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete data to database
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(EditToDoActivity.this, MainActivity.class);
                            startActivity(a);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to delete entry", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
