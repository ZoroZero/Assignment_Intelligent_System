package com.example.assignment_personality_predict;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment_personality_predict.helper.Constants;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {
    private TextView resultText;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.resultText);
        ImageView personalityImage = findViewById(R.id.personality);
        TextView personalityDescription = findViewById(R.id.personalityDescription);

        String result = Objects.requireNonNull(getIntent().getStringExtra("result"));
        resultText.setText("Your personality is " + result);
        personalityDescription.setText(getDescription(result));
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

    private String getDescription(String result){
        switch (result){
            case "serious": return Constants.SERIOUS_DESCRIPTION;
            case "lively": return Constants.LIVELY_DESCRIPTION;
            case "dependable": return Constants.DEPENDABLE_DESCRIPTION;
            case "responsible": return Constants.RESPONSIBLE_DESCRIPTION;
            case "extraverted": return Constants.EXTRAVERTED_DESCRIPTION;
            default: return "";
        }
    }
}