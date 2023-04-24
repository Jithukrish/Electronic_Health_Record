package com.example.ehr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    EditText username1;
    EditText password1;
    Button loginbutton,btn;
    SharedPreferences sh;
    String username,password,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username1=findViewById(R.id.username);
        password1=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginButton);
        btn=findViewById(R.id.reg);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=username1.getText().toString();
                password=password1.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                url = "http://" + sh.getString("ip", "") + ":5000/android_login";

                Toast.makeText(MainActivity.this, ""+url, Toast.LENGTH_SHORT).show();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {

                                String lid = json.getString("id");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("lid", lid);
                                edp.commit();

                                Intent ik = new Intent(getApplicationContext(), uhome.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("username", username);
                        params.put("password", password);

                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(ik);

                }

        });
    }
}