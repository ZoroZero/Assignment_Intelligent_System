package com.example.assignment_personality_predict;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {
    private TextView resultText;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.resultText);
        String result = Objects.requireNonNull(getIntent().getStringExtra("result"));
        resultText.setText(result);
    }
}