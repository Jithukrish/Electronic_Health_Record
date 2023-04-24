package com.example.ehr;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ehr.databinding.ActivityUhomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class uhome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener,AdapterView.OnItemClickListener {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityUhomeBinding binding;
    SharedPreferences sh;
    SearchView s1 = null;
     SearchView.OnQueryTextListener queryTextListener;
    String url;
    TextView t1,t2;
    ImageView i1;
    ListView l1;
    ArrayList<String> name,rating,email,phn,adrs,type,lid,lattitude,longitude,place,image,schedule;
    String type1[]={"Hospital","doctor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUhomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setSupportActionBar(binding.appBarUhome.toolbar);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


//        t2=(TextView)findViewById(R.id.textView);
        userinfo();


        binding.appBarUhome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_uhome);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(uhome.this);
         t1 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.t1);
         t2 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);
         i1= (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        s1=(SearchView) navigationView.getHeaderView(0).findViewById(R.id.s1);
        l1=findViewById(R.id.l1);
        s1=findViewById(R.id.s1);

        s1.setOnQueryTextListener(this);
        s1.setIconified(false);
        s1.setIconifiedByDefault(false);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(),type.get(i),Toast.LENGTH_LONG).show();
        if (type.get(i).equals("doctor")) {
            Intent in = new Intent(getApplicationContext(), view_doctor.class);
            in.putExtra("nm", name.get(i));
            in.putExtra("adrs", adrs.get(i));
            in.putExtra("email", email.get(i));
            in.putExtra("ph", phn.get(i));
            in.putExtra("latti", lattitude.get(i));
            in.putExtra("longi", longitude.get(i));
            in.putExtra("place", place.get(i));
            in.putExtra("img", image.get(i));
            in.putExtra("schedule", schedule.get(i));


            startActivity(in);
        } else {
            Intent in = new Intent(getApplicationContext(), view_hospital.class);
            in.putExtra("nm", name.get(i));
            in.putExtra("img", adrs.get(i));
            in.putExtra("email", email.get(i));
            in.putExtra("ph", phn.get(i));
            in.putExtra("latti", lattitude.get(i));
            in.putExtra("longi", longitude.get(i));
            in.putExtra("place", place.get(i));
            in.putExtra("img", image.get(i));
            startActivity(in);


        }
    }
    private void userinfo() {

        RequestQueue queue = Volley.newRequestQueue(uhome.this);
        url = "http://" + sh.getString("ip", "") + ":5000/new_user";
        Toast.makeText(uhome.this, ""+url, Toast.LENGTH_SHORT).show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    JSONObject json = new JSONObject(response);
                    String name = json.getString("name");
                    String email = json.getString("email");
                    String img = json.getString("img");

                    if(android.os.Build.VERSION.SDK_INT>9)
                    {
                        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                    }
                    t1.setText(name);
                    t2.setText(email);
                    java.net.URL thumb_u;
                    try {

                        //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

                        thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/media/"+img);
                        Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
                        i1.setImageDrawable(thumb_d);

                    }
                    catch (Exception e)
                    {
                        Log.d("errsssssssssssss",""+e);
                    }//                    if (res.equalsIgnoreCase("valid")) {

//                        String lid = json.getString("id");
//                        SharedPreferences.Editor edp = sh.edit();
//                        edp.putString("lid", lid);
//                        edp.commit();

//                        Intent ik = new Intent(getApplicationContext(), uhome.class);
//                        startActivity(ik);

//                    } else {
//
////                        Toast.makeText(uhome.this, "Invalid", Toast.LENGTH_SHORT).show();
//
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("lid",sh.getString("lid",""));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.uhome, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setQueryHint("Cari");


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_uhome);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.search)
        {
            Intent i=new Intent(getApplicationContext(),search.class);
            startActivity(i);

        }

        if(id==R.id.view_schedule)
        {
            Intent i=new Intent(getApplicationContext(),user_view_schedule1.class);
            startActivity(i);

        }
        if(id==R.id.download_record)
        {
            Intent i=new Intent(getApplicationContext(),record_listview.class);
            startActivity(i);

        }
        if(id==R.id.rating)
        {
            Intent i=new Intent(getApplicationContext(),add_rate_after_list.class);
            startActivity(i);

        }
        if(id==R.id.nav_feedback)
        {
            Intent i=new Intent(getApplicationContext(),feedback_user.class);
            startActivity(i);

        }
        if(id==R.id.comp)
        {
            Intent i=new Intent(getApplicationContext(),send_complaints.class);
            startActivity(i);

        }







        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        url ="http://"+sh.getString("ip", "") + ":5000/android_search_view";
        RequestQueue queue = Volley.newRequestQueue(uhome.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                 Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    rating= new ArrayList<>();
                    email= new ArrayList<>();
                    phn=new ArrayList<>();
                    adrs=new ArrayList<>();
                    place=new ArrayList<>();
                    type=new ArrayList<>();
                    lid=new ArrayList<>();
                    lattitude=new ArrayList<>();
                    longitude=new ArrayList<>();
                    image=new ArrayList<>();
                    schedule=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        rating.add(jo.getString("rating"));
                        email.add(jo.getString("email"));
                        phn.add(jo.getString("phn"));
                        adrs.add(jo.getString("adrs"));
                        type.add(jo.getString("type"));
                        lid.add(jo.getString("id"));
                        place.add(jo.getString("place"));
                        lattitude.add(jo.getString("latti"));
                        longitude.add(jo.getString("longi"));
                        image.add(jo.getString("img"));
                        schedule.add(jo.getString("schedule"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new customhospital(uhome.this,name,rating,adrs,email,phn,type,lid,lattitude,longitude,image,place));
                    l1.setOnItemClickListener((AdapterView.OnItemClickListener) uhome.this);
                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(uhome.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("txt",s);
                return params;
            }
        };
        queue.add(stringRequest);
        return false;
    }
}