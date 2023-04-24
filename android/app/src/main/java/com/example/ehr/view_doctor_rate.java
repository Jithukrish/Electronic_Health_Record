package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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

public class view_doctor_rate extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener{
    Spinner spinner;
    Button C;

    SharedPreferences sh;
    SearchView s1;

    ListView l1;
    ArrayList<String> name,email,phn,adrs,type,lid,lattitude,longitude,place,image,departments;
    String url;
    String type1[]={"doctor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_rate);
        s1=findViewById(R.id.s1);
        s1.setOnQueryTextListener(this);
        l1=findViewById(R.id.l1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        url = "http://" + sh.getString("ip", "") + ":5000/add_rating";
        RequestQueue queue = Volley.newRequestQueue(view_doctor_rate.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);
                    name = new ArrayList<>();
//                    rating= new ArrayList<>();
                    email = new ArrayList<>();
                    phn = new ArrayList<>();
                    adrs = new ArrayList<>();
                    place = new ArrayList<>();
                    type = new ArrayList<>();
                    lid = new ArrayList<>();
                    lattitude = new ArrayList<>();
                    longitude = new ArrayList<>();
                    image = new ArrayList<>();
//                    departments = new ArrayList<>();

                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        name.add(jo.getString("name"));
//                        rating.add(jo.getString("rating"));
                        email.add(jo.getString("email"));
                        phn.add(jo.getString("phn"));
                        adrs.add(jo.getString("adrs"));
                        type.add(jo.getString("type"));
                        lid.add(jo.getString("id"));
                        place.add(jo.getString("place"));
                        lattitude.add(jo.getString("latti"));
                        longitude.add(jo.getString("longi"));
                        image.add(jo.getString("img"));
//                        departments.add(jo.getString("departments"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new customhospital(view_doctor_rate.this, name, adrs, email, phn, type, lid, lattitude, longitude, image, place, departments));
                    l1.setOnItemClickListener(view_doctor_rate.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_doctor_rate.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("txt", s);
                return params;
            }
        };
        queue.add(stringRequest);

        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(),type.get(i),Toast.LENGTH_LONG).show();
        if(type.get(i).equals("doctor")) {
            Intent in = new Intent(getApplicationContext(), RateUsDialog.class);
            in.putExtra("id",lid.get(i));




            startActivity(in);
        }




    }
    }
