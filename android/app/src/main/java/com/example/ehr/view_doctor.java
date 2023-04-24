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
import android.widget.Toast;

public class view_doctor extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7;
    ImageView i1,i2;
    SharedPreferences sh;
    String did;
    String name,address,phone,email,lattitude,longitude,place,schedule,url,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);
       t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.textView17);


        i1=findViewById(R.id.i1);
        i2=findViewById(R.id.imageView4);

        did=getIntent().getStringExtra("did");

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),view_schedule.class);
                ii.putExtra("did",did);
                startActivity(ii);
            }
        });

        name=getIntent().getStringExtra("nm");
        address=getIntent().getStringExtra("adrs");
        phone=getIntent().getStringExtra("ph");
        email=getIntent().getStringExtra("email");
        place=getIntent().getStringExtra("place");
//        Toast.makeText(this, ""+place, Toast.LENGTH_SHORT).show();
        lattitude=getIntent().getStringExtra("latti");
        longitude=getIntent().getStringExtra("longi");
        image=getIntent().getStringExtra("img");

        t1.setText(name);
        t2.setText(address);
        t3.setText(email);
        t4.setText(phone);
        t6.setText(place);

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