package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;
import java.util.logging.Logger;

public class ThirdActivity extends AppCompatActivity {
    TextView congratulationTextView;
    TextView scoreTextView;
    Button newQuizButton;
    Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String score = intent.getStringExtra("score");

        congratulationTextView = findViewById(R.id.congratsTextViewId);
        congratulationTextView.setText("Congratulations " + name);

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(score + "/5");

        newQuizButton = findViewById(R.id.newQuizButton);
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
                finish();
            }
        });

        finishButton = findViewById(R.id.finishButton);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }

}