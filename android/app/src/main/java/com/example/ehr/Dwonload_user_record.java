package com.example.ehr;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class Dwonload_user_record extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;


    SharedPreferences sh;



    public Dwonload_user_record(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;

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
            gridView=inflator.inflate(R.layout.activity_dwonload_user_record, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.tv1);
        TextView tv2=(TextView)gridView.findViewById(R.id.tv2);
        TextView tv3=(TextView)gridView.findViewById(R.id.tv3);
        Button btn=(Button) gridView.findViewById(R.id.button3);
//        TextVTiew tv5=(TextView)gridView.findViewById(R.id.tvbathroom);
        CardView cv=gridView.findViewById(R.id.cv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url3="http://"+sh.getString("ip","")+":5000"+d.get(position);
                Toast.makeText(context, url3, Toast.LENGTH_SHORT).show();

                Intent dwnl=new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                context.startActivity(dwnl);

            }
        });

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
        tv2.setText(b.get(position));
        tv3.setText(c.get(position));


//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if(f.get(position).equalsIgnoreCase("hospital")) {
////                    Intent in = new Intent(context, view_hospital.class);
////                    in.putExtra("day",a.get(position));
////                    in.putExtra("ftime",b.get(position));
////                    in.putExtra("ttime",c.get(position));
////
////                    context.startActivity(in);
////
////                }
////                else
////                {
//                Intent in = new Intent(context, view_doctor.class);
//                in.putExtra("dname",a.get(position));
//                in.putExtra("hname",b.get(position));
//                in.putExtra("date",c.get(position));
//
//
//
//
//                context.startActivity(in);
//
//            }
//        });




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);












        return gridView;

    }

}





