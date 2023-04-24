package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class doc_user_schedule extends AppCompatActivity {

    ListView l1;
    SharedPreferences sh;
    String hiid;
    ArrayList<String> hospital,user,phone,address,email,date,time,bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_schedule1);

        l1=findViewById(R.id.l1);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "")+":5000/user_view_schedule";
        RequestQueue queue = Volley.newRequestQueue(doc_user_schedule.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    hospital= new ArrayList<>();
                    user= new ArrayList<>();
                    address= new ArrayList<>();
                    phone= new ArrayList<>();
                    email= new ArrayList<>();
                    date= new ArrayList<>();
                    time= new ArrayList<>();
                    bid= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        hospital.add(jo.getString("hname"));
                        user.add(jo.getString("dname"));
                        address.add(jo.getString("sloat"));
                        phone.add(jo.getString("hname"));
                        email.add(jo.getString("dname"));
                        date.add(jo.getString("sloat"));
                        time.add(jo.getString("date"));
                        bid.add(jo.getString("id"));

                    }
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(user_view_schedule1.this,android.R.layout.simple_list_item_1,hname);
//                    sp.setAdapter(ad);
//                    sp.setOnItemSelectedListener(user_view_schedule1.this);
                    l1.setAdapter(new custom_doc_view_schedule(doc_user_schedule.this,hospital,user,address,phone,email,date,bid));
//                    l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(doc_user_schedule.this, "+++"+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(doc_user_schedule.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);




    }










}