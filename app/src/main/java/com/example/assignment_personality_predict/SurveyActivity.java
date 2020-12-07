package com.example.assignment_personality_predict;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;

import com.example.assignment_personality_predict.requestHandler.RequestService;
import com.example.assignment_personality_predict.requestHandler.Volley_CallBack;

import java.util.Objects;

public class SurveyActivity extends AppCompatActivity implements Volley_CallBack {
    private int openness;
    private int neuroticis;
    private int agreeableness;
    private int extraversion;
    private int conscientiousness;
    private int age;
    private int gender;


    Button submitBtn;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        RatingBar neuroticisTV = findViewById(R.id.Neuroticis_Rating);
        RatingBar agreeablenessTV = findViewById(R.id.Agreeableness_Rating);
        RatingBar conscientiousnessTV = findViewById(R.id.Conscientiousness_Rating);
        RatingBar opennessTV = findViewById(R.id.Openness_Rating);
        RatingBar extraversionTV = findViewById(R.id.Extraversion_Rating);

        age = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("age")));
        gender = Objects.requireNonNull(getIntent().getIntExtra("gender", 1));

        submitBtn = findViewById(R.id.Submit_button);
        progressBarHolder = findViewById(R.id.progressBarHolder);


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

    void sendDataToServer(){
        this.startLoading();
        RequestService.GetPrediction(age, this.gender,
                this.neuroticis, this.agreeableness, this.conscientiousness,
                this.openness, this.extraversion,getApplicationContext(),SurveyActivity.this);
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
                showResult.putExtra("result", result);
                startActivity(showResult);
                finish();
            }
        }.start();
    }
}