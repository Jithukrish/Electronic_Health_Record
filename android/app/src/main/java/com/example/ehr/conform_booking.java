package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class conform_booking extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7, t8;
    Button button3;
    SharedPreferences sh;
    String url;
    String sid,sloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_booking);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        button3 = findViewById(R.id.button3);
        sid=getIntent().getStringExtra("sid");
        sloat=getIntent().getStringExtra("sloat");
        t6.setText(sh.getString("date",""));
        t8.setText(sloat);

        t2.setText(getIntent().getStringExtra("dn"));
        t4.setText(getIntent().getStringExtra("hn"));
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(conform_booking.this);
                url = "http://" + sh.getString("ip", "") + ":5000/android_confirm_booking";

                Toast.makeText(conform_booking.this, ""+url, Toast.LENGTH_SHORT).show();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("result");

                            if (res.equalsIgnoreCase("ok")) {

                                Toast.makeText(conform_booking.this, "Booking Completed", Toast.LENGTH_SHORT).show();
Intent ik=new Intent(getApplicationContext(),uhome.class);
startActivity(ik);

                            } else {

                                Toast.makeText(conform_booking.this, "Booking Failed", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("lid", sh.getString("lid",""));
                        params.put("sid", sid);
                        params.put("sloat", sloat);
                        params.put("date", sh.getString("date",""));

                        return params;
                    }
                };
                queue.add(stringRequest);



            }
        });

    }
}