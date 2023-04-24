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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class customhospital extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
    ArrayList<String> f;
    ArrayList<String> g;
    ArrayList<String> h;
    ArrayList<String> i;
    ArrayList<String> j;
    ArrayList<String> k;

    SharedPreferences sh;



    public customhospital(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, ArrayList<String> g, ArrayList<String> h, ArrayList<String> i, ArrayList<String> j, ArrayList<String> k) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;
        this.h=h;
        this.i=i;
        this.j=j;
        this.k=k;

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
            gridView=inflator.inflate(R.layout.activity_customhospital, null);
        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.tvroom);
        TextView tv2=(TextView)gridView.findViewById(R.id.tvhall);
        TextView tv3=(TextView)gridView.findViewById(R.id.tvbalcony);
        TextView tv4=(TextView)gridView.findViewById(R.id.tvbalcony1);
        TextView tv5=(TextView)gridView.findViewById(R.id.tvbathroom);
        RatingBar r1=(RatingBar)gridView.findViewById(R.id.ratingBar);
        CardView cv=gridView.findViewById(R.id.cv);
        r1.setRating(Float.parseFloat(b.get(position)));
        r1.setIsIndicator(true);

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
        tv3.setText(d.get(position));
        tv4.setText(e.get(position));
        if(f.get(position).equalsIgnoreCase("hospital")) {
            tv5.setText("24 Hour");
        }
        else
        {

            tv5.setText("");
        }

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(f.get(position).equalsIgnoreCase("hospital")) {
                        Intent in = new Intent(context, view_hospital.class);
                        in.putExtra("nm",a.get(position));
                        in.putExtra("adrs",c.get(position));
                        in.putExtra("email",d.get(position));
                        in.putExtra("ph",e.get(position));
                      in.putExtra("latti",h.get(position));
                        in.putExtra("longi",i.get(position));
                        in.putExtra("img",j.get(position));
                        in.putExtra("place",k.get(position));
                        in.putExtra("hid",g.get(position));
                        context.startActivity(in);

                    }
                    else
                    {

                        tv5.setText("");
                        Intent in = new Intent(context, view_doctor.class);
                        in.putExtra("nm",a.get(position));
                        in.putExtra("adrs",c.get(position));
                        in.putExtra("email",d.get(position));
                        in.putExtra("ph",e.get(position));
                        in.putExtra("latti",h.get(position));
                        in.putExtra("longi",i.get(position));
                        in.putExtra("img",j.get(position));
                        in.putExtra("place",k.get(position));
                        in.putExtra("did",g.get(position));



                        context.startActivity(in);
                    }
                }
            });




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);











        return gridView;

    }

}





