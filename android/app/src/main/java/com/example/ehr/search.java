package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class search extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener{
    Spinner spinner;
    Button C;

    SharedPreferences sh;
    SearchView s1;

    ListView l1;
    ArrayList<String>name,rating,email,phn,adrs,type,lid,lattitude,longitude,place,image,schedule;
    String url;
    String type1[]={"Hospital","doctor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        s1=findViewById(R.id.s1);
        s1.setOnQueryTextListener(this);
        l1=findViewById(R.id.l1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//         ArrayAdapter<String> ad=new ArrayAdapter<>(search.this,android.R.layout.simple_list_item_1,type1);
//        spinner.setAdapter(ad);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        url ="http://"+sh.getString("ip", "") + ":5000/android_search_view";
        RequestQueue queue = Volley.newRequestQueue(search.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    rating= new ArrayList<>();
                    email= new ArrayList<>();
                    phn=new ArrayList<>();
                    adrs=new ArrayList<>();
                    place=new ArrayList<>();
                    type=new ArrayList<>();
                    lid=new ArrayList<>();
                    lattitude=new ArrayList<>();
                    longitude=new ArrayList<>();
                    image=new ArrayList<>();
                    schedule=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        rating.add(jo.getString("rating"));
                        email.add(jo.getString("email"));
                        phn.add(jo.getString("phn"));
                        adrs.add(jo.getString("adrs"));
                        type.add(jo.getString("type"));
                        lid.add(jo.getString("id"));
                        place.add(jo.getString("place"));
                        lattitude.add(jo.getString("latti"));
                        longitude.add(jo.getString("longi"));
                        image.add(jo.getString("img"));
                        schedule.add(jo.getString("schedule"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new customhospital(search.this,name,rating,adrs,email,phn,type,lid,lattitude,longitude,image,place));
                        l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(search.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("txt",s);
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
            Intent in = new Intent(getApplicationContext(), view_doctor.class);
            in.putExtra("nm",name.get(i));
            in.putExtra("adrs",adrs.get(i));
            in.putExtra("email",email.get(i));
            in.putExtra("ph",phn.get(i));
            in.putExtra("latti",lattitude.get(i));
            in.putExtra("longi",longitude.get(i));
            in.putExtra("place",place.get(i));
            in.putExtra("img",image.get(i));
            in.putExtra("schedule",schedule.get(i));



            startActivity(in);
        }
        else
        {
            Intent in = new Intent(getApplicationContext(), view_hospital.class);
            in.putExtra("nm",name.get(i));
            in.putExtra("img",adrs.get(i));
            in.putExtra("email",email.get(i));
            in.putExtra("ph",phn.get(i));
            in.putExtra("latti",lattitude.get(i));
            in.putExtra("longi",longitude.get(i));
            in.putExtra("place",place.get(i));
            in.putExtra("img",image.get(i));
            startActivity(in);


        }
    }
}