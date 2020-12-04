package com.example.assignment_personality_predict.requestHandler;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.assignment_personality_predict.helper.Constants;


public class RequestService {

    // Login function
    public static void GetPrediction(final int age, final int gender, final int neuroticis,
                             final int conscientiousness, final int agreeableness,
                             final int openness, final int extraversion, final Context context,final Volley_CallBack callBack){
        String database_ip = Constants.AI_SERVER_URL;
        final String url = database_ip +"/api?age=" + age + "&gender=" + gender + "&openness=" + openness +
                "&neuroticism=" + neuroticis + "&conscientiousness=" + conscientiousness +
                "&agreeableness="+ agreeableness + "&extraversion=" + extraversion;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                            callBack.onSuccessResponse(response);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){

        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }
}
