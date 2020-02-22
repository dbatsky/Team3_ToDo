package com.example.team3_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Button btnSave;
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
        setContentView(R.layout.activity_settings);

        Switch darkModeSwitch = findViewById(R.id.darkSwitch);
        btnSave = findViewById(R.id.btnSave);

        if (sharedPref.loadNightModeState()) {
            darkModeSwitch.setChecked(true);
        }

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPref.SetNightModeState(true);
                    recreate();
                } else {
                    sharedPref.SetNightModeState(false);
                    recreate();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(a);
            }
        });
    }
}
