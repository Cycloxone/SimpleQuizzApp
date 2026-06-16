package com.example.simplequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private RadioButton yesRadioButton, noRadioButton;
    private Button cheatButton, nextButton, submitButton;

    private String[] questions = {
            "Is Android based on Linux?",
            "Is Kotlin the official language for Android development?",
            "Does Android support Java?",
            "Is Android Studio the official IDE for Android?",
            "Is Firebase a Google product?"
    };

    private boolean[] answers = {
            true,
            true,
            true,
            true,
            true
    };

    private boolean[] userAnswers = new boolean[5];
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        yesRadioButton = findViewById(R.id.yesRadioButton);
        noRadioButton = findViewById(R.id.noRadioButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);

        displayQuestion();

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", answers[currentQuestionIndex]);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
                displayQuestion();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                calculateScore();
                currentQuestionIndex = 0;
                displayQuestion();
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        answerRadioGroup.clearCheck();

        if (currentQuestionIndex == questions.length - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }

    private void saveAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.yesRadioButton) {
            userAnswers[currentQuestionIndex] = true;
        } else if (selectedId == R.id.noRadioButton) {
            userAnswers[currentQuestionIndex] = false;
        }
    }

    private void calculateScore() {
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (userAnswers[i] == answers[i]) {
                score++;
            }
        }
        Toast.makeText(this, "Your score: " + score + "/" + questions.length, Toast.LENGTH_LONG).show();
    }
}