package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

public class record_listview extends AppCompatActivity {
    TextView t1, t2, t3;
    Button btn;
    ListView ls;
    //     CardView cv;
    SharedPreferences sh;

    ArrayList<String> dname, hname, date,record;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_listview);
        ls=findViewById(R.id.l1);
        t1 = findViewById(R.id.tv1);
        t2 = findViewById(R.id.tv2);
        t3 = findViewById(R.id.tv3);

//        CardView cv= GridLayout.findViewById(R.id.cv);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String url1 ="http://"+sh.getString("ip", "")+ ":5000/user_download_file";
        RequestQueue queue = Volley.newRequestQueue(record_listview.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
                    JSONArray ar=new JSONArray(response);

                    dname= new ArrayList<>();
                    hname=new ArrayList<>();
                    date=new ArrayList<>();
                    record=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        dname.add(jo.getString("dname"));
                        hname.add(jo.getString("hname"));
                        date.add(jo.getString("date"));
                        record.add(jo.getString("record"));
//                        Toast.makeText(view_schedule.this, "hello"+ttime, Toast.LENGTH_SHORT).show();
                    }
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(view_schedule.this,android.R.layout.simple_list_item_1,hname);
//                    sp.setAdapter(ad);
                    ls.setAdapter(new Dwonload_user_record(record_listview.this,dname,hname,date,record));
//                    l1.setOnItemClickListener(search.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(record_listview.this, ""+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(record_listview.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));
//                params.put("hid",hiid);
                return params;
            }
        };
        queue.add(stringRequest);


    }




}