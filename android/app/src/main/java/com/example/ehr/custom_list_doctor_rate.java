package com.example.ehr;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class custom_list_doctor_rate extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
//    ArrayList<String> dn;
//    ArrayList<String> hn;


    SharedPreferences sh;



    public custom_list_doctor_rate(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
//        this.dn=dn;
//        this.hn=hn;

        sh=PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_list_doctor_rate, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.tv1);
        TextView tv2=(TextView)gridView.findViewById(R.id.tv2);
//        TextView tv2=(TextView)gridView.findViewById(R.id.tv2);


        CardView cv=gridView.findViewById(R.id.cv);

//        java.net.URL thumb_u;
//        try {
//
//            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");
//
//            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/static/Product/"+b.get(position));
//            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
//            i1.setImageDrawable(thumb_d);
//
//        }
//        catch (Exception e)
//        {
//            Log.d("errsssssssssssss",""+e);
//        }


        tv1.setText(a.get(position));
        tv2.setText(c.get(position));

//        if(b.get(position).equals("confirm")) {
//            tv4.setBackgroundResource(R.drawable.ava);
//        }
//        else
//        {
//            tv4.setBackgroundResource(R.drawable.notav);
//        }



        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    Intent in = new Intent(context, RateUsDialog.class);
                    in.putExtra("dname",a.get(position));
                    in.putExtra("did",b.get(position));
                    in.putExtra("specilization",c.get(position));
//                    in.putExtra("hn",hn.get(position));





                    context.startActivity(in);
                }



            }
        });




        tv1.setTextColor(Color.WHITE);
        tv2.setTextColor(Color.WHITE);













        return gridView;

    }

}





