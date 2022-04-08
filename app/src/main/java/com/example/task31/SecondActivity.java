package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView welcomeTextView;
    TextView titleTextView;
    TextView questionNumberTextView;
    RadioGroup answerGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;


    public Integer currentAnswer;
    public Integer currentQuestion = 0;
    public Boolean submitted = false;
    Button submitButton;
    ProgressBar progressBar;
    public Integer score= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        progressBar = findViewById(R.id.progressBarId);

        welcomeTextView = findViewById(R.id.welcomeTextViewId);

        welcomeTextView.setText("Welcome " + name);
        titleTextView = findViewById(R.id.titleTextViewId);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questionNumberTextView.setText("1/5");

        answerGroup = findViewById(R.id.groupradio);
        answer1 = findViewById(R.id.radia_id1);
        answer2 = findViewById(R.id.radia_id2);
        answer3 = findViewById(R.id.radia_id3);
        submitButton = findViewById(R.id.submitButton);
        Question[] questionList = setQuestions();

        progressBar.setProgress(20);
        titleTextView.setText(questionList[currentQuestion].title);
        answer1.setText(questionList[currentQuestion].answer1);
        answer2.setText(questionList[currentQuestion].answer2);
        answer3.setText(questionList[currentQuestion].answer3);
        Intent nextIntent = new Intent(this, ThirdActivity.class);



        answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    default:
                        currentAnswer = 0;
                        break;
                    case R.id.radia_id1:
                        currentAnswer = 1;
                        answer1.setTextColor(Color.WHITE);
                        answer2.setTextColor(Color.BLACK);
                        answer3.setTextColor(Color.BLACK);
                        answer2.setBackgroundColor(Color.WHITE);
                        answer3.setBackgroundColor(Color.WHITE);
                        answer1.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.radia_id2:
                        currentAnswer = 2;
                        answer2.setTextColor(Color.WHITE);
                        answer1.setTextColor(Color.BLACK);
                        answer3.setTextColor(Color.BLACK);
                        answer1.setBackgroundColor(Color.WHITE);
                        answer3.setBackgroundColor(Color.WHITE);
                        answer2.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.radia_id3:
                        currentAnswer = 3;
                        answer3.setTextColor(Color.WHITE);
                        answer2.setTextColor(Color.BLACK);
                        answer1.setTextColor(Color.BLACK);
                        answer1.setBackgroundColor(Color.WHITE);
                        answer2.setBackgroundColor(Color.WHITE);
                        answer3.setBackgroundColor(Color.BLUE);
                        break;
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!submitted){
                    if(currentAnswer == 0) {
                        Toast.makeText(getApplicationContext(),"Please Select your answer",Toast.LENGTH_LONG).show();
                    } else if (currentAnswer == questionList[currentQuestion].answer){
                        score +=1;
                        submitted = true;
                        answerGroup.getChildAt(currentAnswer -1).setBackgroundColor(Color.YELLOW);
                        submitButton.setText("Next");
                    } else {
                        submitted = true;
                        answerGroup.getChildAt(currentAnswer -1).setBackgroundColor(Color.RED);
                        answerGroup.getChildAt(questionList[currentQuestion].answer -1).setBackgroundColor(Color.YELLOW);
                        submitButton.setText("Next");
                    }
                }
                else {
                    if(currentQuestion == 4) {
                        nextIntent.putExtra("score", score.toString());
                        nextIntent.putExtra("name", name);
                        startActivity(nextIntent);
                    } else {
                        currentAnswer = 0;
                        currentQuestion += 1;
                        answer1.setChecked(false);
                        answer2.setChecked(false);
                        answer3.setChecked(false);
                        titleTextView.setText(questionList[currentQuestion].title);
                        answer1.setText(questionList[currentQuestion].answer1);
                        answer2.setText(questionList[currentQuestion].answer2);
                        answer3.setText(questionList[currentQuestion].answer3);
                        answer1.setBackgroundColor(Color.WHITE);
                        answer2.setBackgroundColor(Color.WHITE);
                        answer3.setBackgroundColor(Color.WHITE);
                        answer1.setTextColor(Color.BLACK);
                        answer2.setTextColor(Color.BLACK);
                        answer3.setTextColor(Color.BLACK);
                        progressBar.setProgress((currentQuestion+1) * 20);
                        submitted = false;
                        submitButton.setText("Submit");
                        questionNumberTextView.setText(currentQuestion+1 + "/5");
                    }

                }
            }
        });
    }



    public Question[] setQuestions() {
        Question[] questionList;
        questionList = new Question[5];

        questionList[0] = new Question("What is layout in android development",
                "The blueprint of an activity",
                "the Structure of the UI",
                "A type of drawable",
                2);
        questionList[1] = new Question("What is Android 9.0's dessert codename?",
                "Oreo",
                "Lollipop",
                "Pie",
                3);
        questionList[2] = new Question("WWhat order are the three lifecycle callbacks called in?",
                "onStart(), onCreate(), onResume()",
                "onResume(), onCreate(), onStart()",
                "onCreate(), onStart(), onResume()",
                3);
        questionList[3] = new Question("What is an activity in android development",
                "Screen.",
                "Action",
                "Function",
                1);
        questionList[4] = new Question("what does the finish() function do?",
                "destroy the activity",
                "pop the screen out of the activity stack",
                "both above answers",
                3);
        return questionList;
    }
}

class Question {
    public String title;
    public String answer1;
    public String answer2;
    public String answer3;
    public Integer answer;

    Question(String title, String answer1, String answer2, String answer3, Integer answer){
        this.title = title;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer = answer;
    }
}