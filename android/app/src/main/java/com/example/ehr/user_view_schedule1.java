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

public class user_view_schedule1 extends AppCompatActivity {

    ListView l1;
    SharedPreferences sh;
    String hiid;
    ArrayList<String> hname,dname,sloat,date,bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_schedule1);

        l1=findViewById(R.id.l1);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url ="http://"+sh.getString("ip", "")+":5000/user_view_schedule";
        RequestQueue queue = Volley.newRequestQueue(user_view_schedule1.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    hname= new ArrayList<>();
                    dname= new ArrayList<>();
                    sloat= new ArrayList<>();
                    date= new ArrayList<>();
                    bid= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        hname.add(jo.getString("hname"));
                        dname.add(jo.getString("dname"));
                        sloat.add(jo.getString("sloat"));
                        date.add(jo.getString("date"));
                        bid.add(jo.getString("id"));

                    }
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(user_view_schedule1.this,android.R.layout.simple_list_item_1,hname);
//                    sp.setAdapter(ad);
//                    sp.setOnItemSelectedListener(user_view_schedule1.this);
                    l1.setAdapter(new UserHome2(user_view_schedule1.this,hname,dname,sloat,date,bid));
//                    l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(user_view_schedule1.this, "+++"+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(user_view_schedule1.this, "err"+error, Toast.LENGTH_SHORT).show();
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