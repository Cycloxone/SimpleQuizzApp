package com.example.simplequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView answerTextView = findViewById(R.id.answerTextView);
        Button backButton = findViewById(R.id.backButton);

        boolean answer = getIntent().getBooleanExtra("answer", false);
        answerTextView.setText("Answer: " + (answer ? "Yes" : "No"));

        backButton.setOnClickListener(v -> finish());
    }
}