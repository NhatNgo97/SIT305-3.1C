package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign name
        nameEditText = findViewById(R.id.nameId);

        //assign button
        startButton = findViewById(R.id.startButton);

    }

    public void toSecondActivity(View view) {
        if (nameEditText.getText().toString().trim().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", nameEditText.getText().toString());
            startActivity(intent);
        }
    }
}