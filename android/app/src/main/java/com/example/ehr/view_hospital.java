package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class view_hospital extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    ImageView i1,i2;
    SharedPreferences sh;
    String name,address,phone,email,lattitude,longitude,schedule,place,url,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospital);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        i1=findViewById(R.id.i1);
        i2=findViewById(R.id.imageView4);
        t1.setText(getIntent().getStringExtra("nm"));
        t2.setText(getIntent().getStringExtra("adrs"));
        t3.setText(getIntent().getStringExtra("email"));
        t4.setText(getIntent().getStringExtra("ph"));
        t5.setText(getIntent().getStringExtra("place"));
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lattitude=getIntent().getStringExtra("latti");
        longitude=getIntent().getStringExtra("longi");
        image=getIntent().getStringExtra("img");
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/media/"+image);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.com/maps?q="+lattitude+","+longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);

            }
        });


    }
}