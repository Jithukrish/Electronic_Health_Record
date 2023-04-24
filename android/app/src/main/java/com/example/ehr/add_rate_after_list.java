package com.example.ehr;
import static com.example.ehr.R.id.r1;
import static com.example.ehr.R.id.ratingBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class add_rate_after_list extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    RatingBar r1;
Spinner sp;
    Button rateNowBtn;
    ArrayList<String> dname,did,specilization;
    SharedPreferences sh;
    SearchView s1;

    ListView l1;
    String url, ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_rating_after_list);
        r1 = findViewById(R.id.ratingBar);
//        sp=findViewById(R.id.spinner);
//        s1=findViewById(R.id.s1);
//        s1.setOnQueryTextListener(this);
        l1=findViewById(R.id.l1);
        rateNowBtn = findViewById(R.id.rateNowBtn);
//        sp.setOnItemSelectedListener(this);
//        loginbutton=findViewById(R.id.loginButton);
//        btn=findViewById(R.id.reg);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = "http://" + sh.getString("ip", "") + ":5000/view_rate_us";

        RequestQueue queue = Volley.newRequestQueue(add_rate_after_list.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(add_rate_after_list.this, "eeeeeeeeee"+response, Toast.LENGTH_SHORT).show();
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    dname= new ArrayList<>();
                    specilization=new ArrayList<>();
                    did=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        dname.add(jo.getString("name"));
                        did.add(jo.getString("did"));
                        specilization.add(jo.getString("specilization"));


                    }

//                    ArrayAdapter<String> ad=new ArrayAdapter<>(add_rate_after_list.this,android.R.layout.simple_list_item_1,dname);
//                    sp.setAdapter(ad);
//                    sp.setOnItemSelectedListener(add_rate_after_list.this);
                    l1.setAdapter(new custom_list_doctor_rate(add_rate_after_list.this,dname,did,specilization));
                    l1.setOnItemClickListener(add_rate_after_list.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(add_rate_after_list.this, "+++"+e, Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(add_rate_after_list.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("did",getIntent().getStringExtra("did"));
//                params.put("dname",getIntent().getStringExtra("dname"));
//                params.put("speciliztaion",getIntent().getStringExtra("speciliztaion"));
                params.put("lid",sh.getString("lid",""));


                return params;
            }
        };
        queue.add(stringRequest);





    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String drid=did.get(i);
        Intent ik=new Intent(getApplicationContext(),RateUsDialog.class);
        ik.putExtra("docid",drid);
        startActivity(ik);

    }
}
