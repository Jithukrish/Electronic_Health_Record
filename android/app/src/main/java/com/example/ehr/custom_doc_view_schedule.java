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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class custom_doc_view_schedule extends BaseAdapter {
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


    SharedPreferences sh;
    String url;



    public custom_doc_view_schedule(Context applicationContext, ArrayList<String> a, ArrayList<String> b,ArrayList<String> c, ArrayList<String> d, ArrayList<String> e,ArrayList<String> f, ArrayList<String> g) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;



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
            gridView=inflator.inflate(R.layout.activity_user_view_schedule, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv2=(TextView)gridView.findViewById(R.id.tv2);
        TextView tv4=(TextView)gridView.findViewById(R.id.tv4);
        TextView tv6=(TextView)gridView.findViewById(R.id.tv6);
        TextView tv8=(TextView)gridView.findViewById(R.id.tv8);
        TextView tv10=(TextView)gridView.findViewById(R.id.tv10);
        TextView tv12=(TextView)gridView.findViewById(R.id.tv12);
        TextView tv14=(TextView)gridView.findViewById(R.id.tv14);

        Button b1=(Button) gridView.findViewById(R.id.button5);
        Button b2=(Button) gridView.findViewById(R.id.button4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(context);
                url = "http://" + sh.getString("ip", "") + ":5000/confirm";

                Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {

                                Toast.makeText(context, "confirm", Toast.LENGTH_SHORT).show();


                                Intent ik = new Intent(context.getApplicationContext(), uhome.class);
                                context.startActivity(ik);

                            } else {

                                Toast.makeText(context, "confirm booking ", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context.getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("bid", e.get(position));

                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(context);
                url = "http://" + sh.getString("ip", "") + ":5000/cancel";

                Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {

                                Toast.makeText(context, "cancelled", Toast.LENGTH_SHORT).show();


                                Intent ik = new Intent(context.getApplicationContext(), uhome.class);
                                context.startActivity(ik);

                            } else {

                                Toast.makeText(context, "cancelled booking", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context.getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("bid", e.get(position));

                        return params;
                    }
                };
                queue.add(stringRequest);


            }
        });

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


        tv2.setText(a.get(position));
        tv4.setText(b.get(position));
        tv6.setText(c.get(position));
        tv8.setText(d.get(position));
        tv10.setText(e.get(position));
        tv12.setText(f.get(position));
        tv14.setText(g.get(position));



        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(f.get(position).equalsIgnoreCase("hospital")) {
//                    Intent in = new Intent(context, view_hospital.class);
//                    in.putExtra("day",a.get(position));
//                    in.putExtra("ftime",b.get(position));
//                    in.putExtra("ttime",c.get(position));
//
//                    context.startActivity(in);
//
//                }
//                else
//                {
                Intent in = new Intent(context, view_doctor.class);
                in.putExtra("hname",a.get(position));
                in.putExtra("uname",b.get(position));
                in.putExtra("address",c.get(position));
                in.putExtra("phone",d.get(position));
                in.putExtra("email",e.get(position));
                in.putExtra("date",f.get(position));
                in.putExtra("time",g.get(position));





                context.startActivity(in);

            }
        });




        tv2.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        tv8.setTextColor(Color.BLACK);
        tv10.setTextColor(Color.BLACK);
        tv12.setTextColor(Color.BLACK);
        tv14.setTextColor(Color.BLACK);













        return gridView;

    }

}





