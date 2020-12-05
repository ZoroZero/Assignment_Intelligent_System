package com.example.assignment_personality_predict;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_personality_predict.requestHandler.RequestHandler;
import com.example.assignment_personality_predict.requestHandler.RequestService;
import com.example.assignment_personality_predict.requestHandler.Volley_CallBack;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Volley_CallBack {
    private EditText ageTV;
    private RadioGroup radioGenderGroup;
    private int gender = 1;
    private int openness;
    private int neuroticis;
    private int agreeableness;
    private int extraversion;
    private int conscientiousness;

    private RatingBar neuroticisTV;
    private RatingBar agreeablenessTV;
    private RatingBar conscientiousnessTV;
    private RatingBar opennessTV;
    private RatingBar extraversionTV ;
    Button submitBtn;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageTV = findViewById(R.id.Age_Input);
        radioGenderGroup = findViewById(R.id.RadioGenderGroup);
        neuroticisTV = findViewById(R.id.Neuroticis_Rating);
        agreeablenessTV = findViewById(R.id.Agreeableness_Rating);
        conscientiousnessTV = findViewById(R.id.Conscientiousness_Rating);
        opennessTV = findViewById(R.id.Openness_Rating);
        extraversionTV = findViewById(R.id.Extraversion_Rating);

        submitBtn = findViewById(R.id.Submit_button);
        progressBarHolder = findViewById(R.id.progressBarHolder);


        radioGenderGroup.check(R.id.maleGender);

        radioGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnGenderChange(group, checkedId);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });
        // Openness
        opennessTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rb = (RatingBar) v;
                float rating = rb.getRating();
                openness = (int)(rating*2);
            }
        });
        opennessTV.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                openness = (int)(newRating*2);
            }
        });

        // Agreeableness
        agreeablenessTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rb = (RatingBar) v;
                float rating = rb.getRating();
                agreeableness = (int)(rating*2);
            }
        });
        agreeablenessTV.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                agreeableness = (int)(newRating*2);
            }
        });

        // Extraversion
        extraversionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rb = (RatingBar) v;
                float rating = rb.getRating();
                extraversion = (int)(rating*2);
            }
        });
        extraversionTV.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                extraversion = (int)(newRating*2);
            }
        });

        // Neuroticis
        neuroticisTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rb = (RatingBar) v;
                float rating = rb.getRating();
                neuroticis = (int)(rating*2);
            }
        });
        neuroticisTV.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                neuroticis = (int)(newRating*2);
            }
        });

        // Conscientiousness
        conscientiousnessTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rb = (RatingBar) v;
                float rating = rb.getRating();
                conscientiousness = (int)(rating*2);
            }
        });
        conscientiousnessTV.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                conscientiousness = (int)(newRating*2);
            }
        });
    }

    void sendDataToServer(){
        final String age = ageTV.getText().toString();
        this.startLoading();
        RequestService.GetPrediction(Integer.parseInt(age) , this.gender,
                this.neuroticis, this.agreeableness, this.conscientiousness,
                this.openness, this.extraversion,getApplicationContext(),MainActivity.this);
    }

    private void startLoading(){
        submitBtn.setEnabled(false);
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    private void stopLoading(){
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
        submitBtn.setEnabled(true);
    }

    @Override
    public void onSuccessResponse(final String result) {

        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                stopLoading();
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent showResult = new Intent(getApplicationContext(), ResultActivity.class);
                showResult.putExtra("result",
                        result);
                startActivity(showResult);
                finish();
            }
        }.start();
    }

    private void doOnGenderChange(RadioGroup group, int checkedId){
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.maleGender) {
            this.gender = 1;
        }
        else if(checkedRadioId== R.id.femaleGender) {
            this.gender = 0;
        }
    }

}