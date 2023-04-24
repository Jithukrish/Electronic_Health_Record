package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
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

public class ipaddress extends AppCompatActivity {
    EditText e;
    Button bt;
    SharedPreferences sh;
    String ipa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipaddress);
        e=findViewById(R.id.et);
        bt=findViewById(R.id.btn);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e.setText(sh.getString("ip",""));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipa=e.getText().toString();
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("ip",ipa);
                ed.commit();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });


    }


}