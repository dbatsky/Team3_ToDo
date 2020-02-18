package com.example.team3_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class AddNewActivity extends AppCompatActivity {

    String name, description, date;

    EditText nameInput;
    EditText descriptionInput;
    EditText dateInput;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        nameInput = (EditText) findViewById(R.id.nameInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        dateInput = findViewById(R.id.dateInput);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                description = descriptionInput.getText().toString();
                date = dateInput.getText().toString();

                todos todo = new todos(name, date, description);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("todo", todo);
                setResult(Activity.RESULT_OK, returnIntent);


                finish();
            }
        });

    }

    private void showToast(String text) {
        Toast.makeText(AddNewActivity.this, text, Toast.LENGTH_LONG).show();
    }
}
