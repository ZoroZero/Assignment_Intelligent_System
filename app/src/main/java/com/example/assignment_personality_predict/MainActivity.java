package com.example.assignment_personality_predict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText ageTV;
    private int gender = 1;

    Button submitBtn;
//    AlphaAnimation inAnimation;
//    AlphaAnimation outAnimation;
//    FrameLayout progressBarHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = findViewById(R.id.Submit_button);

        ageTV = findViewById(R.id.Age_Input);
        RadioGroup radioGenderGroup = findViewById(R.id.RadioGenderGroup);



        radioGenderGroup.check(R.id.maleGender);

        radioGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnGenderChange(group);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSurvey();
            }
        });

    }



//    private void startLoading(){
//        submitBtn.setEnabled(false);
//        inAnimation = new AlphaAnimation(0f, 1f);
//        inAnimation.setDuration(200);
//        progressBarHolder.setAnimation(inAnimation);
//        progressBarHolder.setVisibility(View.VISIBLE);
//    }
//
//    private void stopLoading(){
//        outAnimation = new AlphaAnimation(1f, 0f);
//        outAnimation.setDuration(200);
//        progressBarHolder.setAnimation(outAnimation);
//        progressBarHolder.setVisibility(View.GONE);
//        submitBtn.setEnabled(true);
//    }

    private void goToSurvey(){
        String age = ageTV.getText().toString();
        Intent goToSurvey = new Intent(getApplicationContext(), SurveyActivity.class);
        goToSurvey.putExtra("age", age);
        goToSurvey.putExtra("gender", gender);
        startActivity(goToSurvey);

    }


    private void doOnGenderChange(RadioGroup group){
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.maleGender) {
            this.gender = 1;
        }
        else if(checkedRadioId== R.id.femaleGender) {
            this.gender = 0;
        }
    }

}