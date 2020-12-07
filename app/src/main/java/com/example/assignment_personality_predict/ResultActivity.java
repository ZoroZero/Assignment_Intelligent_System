package com.example.assignment_personality_predict;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
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
        ImageView personalityImage = findViewById(R.id.personality);
        String result = Objects.requireNonNull(getIntent().getStringExtra("result"));
        resultText.setText(result);

        personalityImage.setImageResource(getImage(result));
    }

    private int getImage(String result){
        switch (result){
            case "serious": return R.drawable.serious;
            case "lively": return R.drawable.lively1;
            case "dependable": return R.drawable.dependable;
            case "responsible": return R.drawable.responsible;
            case "extraverted": return R.drawable.extravert;
            default: return R.drawable.icon;
        }
    }
}