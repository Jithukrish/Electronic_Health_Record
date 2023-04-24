package com.example.ehr;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RateUsDialog extends Activity{
    private float userRate = 0;
    RatingBar r1;
     ImageView ratingImage;
    Button b1;
    SharedPreferences sh;
    String url, ratingBar;
//    public RateUsDialog(@NonNull Context context) {
//        super(context);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_us_dialog_layout);

         b1= findViewById(R.id.rateNowBtn);
//        final AppCompatButton lateBtn=findViewById(R.id.laterBtn);
        r1= findViewById(R.id.ratingBar);
         ratingImage=findViewById(R.id.ratingImage);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingBar = String.valueOf(r1.getRating());
//                ratingImage=String.ValueOf(ratingImage.get());
                RequestQueue queue = Volley.newRequestQueue(RateUsDialog.this);
                url = "http://" + sh.getString("ip", "") + ":5000/add_rating";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("result");

                            if (res.equalsIgnoreCase("ok")) {
//
//                                String lid = json.getString("id");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();

                                Intent ik = new Intent(getApplicationContext(), uhome.class);
                                startActivity(ik);
                                Toast.makeText(RateUsDialog.this, "Rating Done ", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(RateUsDialog.this, "Invalid ", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },


                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("ratingbar", ratingBar);
                        params.put("did",getIntent().getStringExtra("did"));
//                        params.put("rating", sh.getString("rating",""));
                        params.put("rating", ratingBar);
                        params.put("lid",sh.getString("lid",""));


//                        params.put("password", password);

                        return params;
                    }
                };
                queue.add(stringRequest);
            }
            });


//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar r1, float rating, boolean fromUser) {
//                 if(rating <= 1){
//                    ratingImage.setImageResource(R.drawable.one_star);
//                }
//                 else if(rating <= 2){
//                     ratingImage.setImageResource(R.drawable.two_star);
//                 }
//                 else if(rating <= 3){
//                     ratingImage.setImageResource(R.drawable.three_star);
//                 }
//                 else if(rating <= 4){
//                     ratingImage.setImageResource(R.drawable.four_star);
//                 }
//                 else if (rating <= 5){
//                     ratingImage.setImageResource(R.drawable.five_star);
//                }
//
//
//                 animateImage(ratingImage);
//
//
//
//                 userRate = rating;
//            }
//        });
//
//    }

//    private Context getApplicationContext() {
//    }
//
//    private void animateImage(ImageView ratingImage){
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f,0,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        scaleAnimation.setFillAfter(true);
//        scaleAnimation.setDuration(200);
//        ratingImage.startAnimation(scaleAnimation);
//
    }
}

