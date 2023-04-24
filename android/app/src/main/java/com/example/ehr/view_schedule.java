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

public class view_schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner sp;
    ListView ls;
    SharedPreferences sh;
    String hiid;
    ArrayList<String> hname,hid,ftime,ttime,day,sid;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        sp=findViewById(R.id.spinner);
        ls=findViewById(R.id.l1);
        b2=findViewById(R.id.button2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sp.setOnItemSelectedListener(this);
        String url ="http://"+sh.getString("ip", "")+":5000/android_view_schedule_hospital";
        RequestQueue queue = Volley.newRequestQueue(view_schedule.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    hname= new ArrayList<>();
                    hid=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        hname.add(jo.getString("hname"));
                        hid.add(jo.getString("lid"));

                    }
                    ArrayAdapter<String> ad=new ArrayAdapter<>(view_schedule.this,android.R.layout.simple_list_item_1,hname);
                    sp.setAdapter(ad);
                    sp.setOnItemSelectedListener(view_schedule.this);
//                    l1.setAdapter(new customhospital(search.this,name,rating,adrs,email,phn,type,lid,lattitude,longitude,image,place));
//                    l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(view_schedule.this, "+++"+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_schedule.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("did",getIntent().getStringExtra("did"));
                return params;
            }
        };
        queue.add(stringRequest);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),booking.class);

//                "did",getIntent().getStringExtra("did"));
//                params.put("hid",hiid
                ik.putExtra("did",getIntent().getStringExtra("did"));
                ik.putExtra("hid",hiid);


                startActivity(ik);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        hiid=hid.get(i);


        String url1 ="http://"+sh.getString("ip", "")+ ":5000/android_view_doctor_schedule";
        RequestQueue queue = Volley.newRequestQueue(view_schedule.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
                    JSONArray ar=new JSONArray(response);

                    day= new ArrayList<>();
                    ftime=new ArrayList<>();
                    ttime=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        day.add(jo.getString("day"));
                        ftime.add(jo.getString("ftime"));
                        ttime.add(jo.getString("ttime"));
//                        Toast.makeText(view_schedule.this, "hello"+ttime, Toast.LENGTH_SHORT).show();
                    }
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(view_schedule.this,android.R.layout.simple_list_item_1,hname);
//                    sp.setAdapter(ad);
                    ls.setAdapter(new custom_view_schedule(view_schedule.this,day,ftime,ttime));
//                    l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(view_schedule.this, ""+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_schedule.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("did",getIntent().getStringExtra("did"));
                params.put("hid",hiid);
                return params;
            }
        };
        queue.add(stringRequest);





    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}