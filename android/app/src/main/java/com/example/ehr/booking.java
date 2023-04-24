package com.example.ehr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class booking extends AppCompatActivity {
    GridView t2;
    final Calendar myCalendar= Calendar.getInstance();
//    String PathHolder="";
//    byte[] filedt=null;
    TextView t1;
    Button button;
    ArrayList<String>sloat,status,dname,hname,sid;
    SharedPreferences sh;
    String url;
    String sel_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        t2= findViewById(R.id.t2);
        t1=findViewById(R.id.t1);
        button =findViewById(R.id.button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String _year = String.valueOf(year);
                        String _month = (month+1) < 10 ? "0" + (month+1) : String.valueOf(month+1);
                        String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String _pickedDate = year + "-" + _month + "-" + _date;
                        Log.e("PickedDate: ", "Date: " + _pickedDate); //2019-02-12
                        t1.setText(_pickedDate);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sel_date=t1.getText().toString();
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("date",sel_date);
                ed.commit();
                Toast.makeText(booking.this, "You Date is "+sel_date, Toast.LENGTH_SHORT).show();

                String url1 ="http://"+sh.getString("ip", "")+ ":5000/android_booking_sloat_select";
                RequestQueue queue = Volley.newRequestQueue(booking.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                 Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {
                            JSONArray ar=new JSONArray(response);
//
                            sloat= new ArrayList<>();
                            status=new ArrayList<>();

                            sid=new ArrayList<>();
                            dname=new ArrayList<>();
                            hname=new ArrayList<>();
//
                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                sloat.add(jo.getString("sloat"));
                                status.add(jo.getString("status"));

                                  sid.add(jo.getString("sid"));
                                  dname.add(jo.getString("dname"));
                                  hname.add(jo.getString("hname"));

                           }

                            t2.setAdapter(new custom_booking(booking.this,sloat,status,sid,dname,hname));
//                    l1.setOnItemClickListener(search.this);
                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                            Toast.makeText(booking.this, ""+e, Toast.LENGTH_SHORT).show();
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(booking.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("did",getIntent().getStringExtra("did"));
                        params.put("hid",getIntent().getStringExtra("hid"));
                        params.put("date",sel_date);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }
    private void updateLabel(){
        String myFormat="YYYY-MM-dd";
        SimpleDateFormat dateFormat=null;
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
            dateFormat = new SimpleDateFormat(myFormat, Locale.US);

        }
        t1.setText(dateFormat.format(myCalendar.getTime()));
    }

    public static class user_view_schedule extends BaseAdapter {
        private Context context;
        ArrayList<String> a;
        ArrayList<String> b;
        ArrayList<String> c;
        ArrayList<String> d;



        SharedPreferences sh;



        public user_view_schedule(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {
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
            LayoutInflater inflator=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);

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
            TextView tv1=(TextView)gridView.findViewById(R.id.tv1);
            TextView tv2=(TextView)gridView.findViewById(R.id.tv2);
            TextView tv3=(TextView)gridView.findViewById(R.id.tv3);
            TextView tv4=(TextView)gridView.findViewById(R.id.tv4);

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
            tv2.setText(b.get(position));
            tv3.setText(c.get(position));
            tv4.setText(d.get(position));


            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    {
                        Intent in = new Intent(context, user_view_schedule.class);
                        in.putExtra("hname",a.get(position));
                        in.putExtra("dname",b.get(position));
                        in.putExtra("bdate",c.get(position));
                        in.putExtra("sloat",d.get(position));

                        context.startActivity(in);

                    }





                }
            });




            tv1.setTextColor(Color.BLACK);
            tv2.setTextColor(Color.BLACK);
            tv3.setTextColor(Color.BLACK);
            tv4.setTextColor(Color.BLACK);












            return gridView;

        }

    }
}