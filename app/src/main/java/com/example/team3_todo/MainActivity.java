package com.example.team3_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    LinearLayout title;
    TextView titlepage, copyright;
    Button btnAddNew;
    DatabaseReference reference;
    RecyclerView todos;
    ArrayList<todos> list;
    todosAdapter todosAdapter;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPref = new SharedPref(this);

        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        title = findViewById(R.id.title);

        titlepage = findViewById(R.id.titlepage);
        copyright = findViewById(R.id.copyright);
        btnAddNew = findViewById(R.id.btnAddNew);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(a);
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, NewToDoActivity.class);
                startActivity(a);
            }
        });

        // Data
        todos = findViewById(R.id.todos);
        todos.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        // unique id (generate first run only)
        if(SharedPref.UNIQUE_ID == null)
            SharedPref.UNIQUE_ID = UUID.randomUUID().toString();

        // Retrieve data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child(SharedPref.UNIQUE_ID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel_Main";
            String description = "Main Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("C1", name, importance);
            channel.setDescription(description);
            /*
             Register the channel with the system; you can't change the importance
             or other notification behaviors after this
            */
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

    }
}
