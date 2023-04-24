package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class send_complaints extends AppCompatActivity {
    EditText c;
    Button btn;
    SharedPreferences sh;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaints);
        c=findViewById(R.id.complaints);
        btn= findViewById(R.id.Button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comp = c.getText().toString();
                url ="http://"+sh.getString("ip", "")+ ":5000/send_comp";
                RequestQueue queue = Volley.newRequestQueue(send_complaints.this);
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
                                Toast.makeText(send_complaints.this, "Successfully send ", Toast.LENGTH_SHORT).show();

                                Intent ik = new Intent(getApplicationContext(), uhome.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(send_complaints.this, "Invalid ", Toast.LENGTH_SHORT).show();

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
//
//                        params.put("uid",getIntent().getStringExtra("uid"));
//
//                        params.put("feedback", getIntent().getStringExtra("feedback"));
                        params.put("lid",sh.getString("lid",""));
                        params.put("hid","");


                        params.put("complaint",comp);

                        return params;
                    }
                };
                queue.add(stringRequest);
//              }
//             };
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent ik = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(ik);





            }
        });

    }
}