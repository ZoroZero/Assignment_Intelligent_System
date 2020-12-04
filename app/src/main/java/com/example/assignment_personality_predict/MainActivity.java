package com.example.assignment_personality_predict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_personality_predict.requestHandler.RequestHandler;
import com.example.assignment_personality_predict.requestHandler.RequestService;
import com.example.assignment_personality_predict.requestHandler.Volley_CallBack;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Volley_CallBack {
    private Button submitBtn;
    private EditText ageTV;
    private EditText genderTV;
    private EditText neuroticisTV;
    private EditText agreeablenessTV;
    private EditText conscientiousnessTV;
    private EditText opennessTV;
    private EditText extraversionTV ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageTV = findViewById(R.id.Age_Input);
        genderTV = findViewById(R.id.Gender_Input);
        neuroticisTV = findViewById(R.id.Neuroticis_Input);
        agreeablenessTV = findViewById(R.id.Agreeableness_Input);
        conscientiousnessTV = findViewById(R.id.Conscientiousness_Input);
        opennessTV = findViewById(R.id.Openness_Input);
        extraversionTV = findViewById(R.id.Extraversion_Input);

        submitBtn = findViewById(R.id.Submit_button);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });
    }

    void sendDataToServer(){
        final String age = ageTV.getText().toString();
        final String gender = genderTV.getText().toString();
        final String neuroticis = neuroticisTV.getText().toString();
        final String agreeableness = agreeablenessTV.getText().toString();
        final String conscientiousness = conscientiousnessTV.getText().toString();
        final String openness = opennessTV.getText().toString();
        final String extraversion = extraversionTV.getText().toString();

        RequestService.GetPrediction(Integer.parseInt(age) , Integer.parseInt(gender),
                Integer.parseInt(neuroticis), Integer.parseInt(agreeableness), Integer.parseInt(conscientiousness),
                Integer.parseInt(openness), Integer.parseInt(extraversion),getApplicationContext(),MainActivity.this);
    }

    @Override
    public void onSuccessResponse(String result) {
//        startLoading();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}