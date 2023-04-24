package com.example.ehr;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ehr.MainActivity;
import com.example.ehr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    EditText F,L,A,D,p,ph,u,pass,eml,photo;
    final Calendar myCalendar= Calendar.getInstance();
    TextView t1,t2,t3;
    String PathHolder="";
    byte[] filedt=null;



    RadioButton r1,r2;
    Button b1,b11;
    String lastname,firstname,address,dob,gender,phone,place,email,photo1,username,password,url,title;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        F=findViewById(R.id.F);
        L=findViewById(R.id.L);
        A=findViewById(R.id.A);
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        ph=findViewById(R.id.ph);
        p=findViewById(R.id.p);
        photo=findViewById(R.id.photo);
        eml=findViewById(R.id.eml);
        u=findViewById(R.id.u);
        pass=findViewById(R.id.pass);
        b1=findViewById(R.id.b1);
        b11=findViewById(R.id.b11);
        D=(EditText) findViewById(R.id.D);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatePickerDialog ss= new DatePickerDialog(MainActivity2.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(2023, 5, 31);
                ss.getDatePicker().setMaxDate(c.getTimeInMillis());

                ss.show();
            }

        });


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = "http://" + sh.getString("ip", "") + ":5000/android_reg";

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastname=L.getText().toString();
                firstname=F.getText().toString();
                address=A.getText().toString();
                phone=ph.getText().toString();
                place=p.getText().toString();
                email=eml.getText().toString();
                dob=D.getText().toString();
                photo1=photo.getText().toString();
                username=u.getText().toString();
                password=pass.getText().toString();


                if (r1.isChecked()) {
                    gender = r1.getText().toString();

                } else
                {
                    gender = r2.getText().toString();
                }
                if (firstname.equalsIgnoreCase("")) {
                    F.setError("enter your first name");
                }
                else if (!firstname.matches("^[a-zA-Z]*$")) {
                    F.setError(" characters  allowed");
                    F.requestFocus();
                }


                else if (lastname.equalsIgnoreCase("")) {
                    L.setError("enter your last name");
                }
                else if (!lastname.matches("^[a-zA-Z]*$")) {
                    L.setError(" characters  allowed");
                    L.requestFocus();
                }
                else if (gender.equalsIgnoreCase("")) {
                    r1.setError("select gender");
                }
                else if (address.equalsIgnoreCase("")) {
                    A.setError("enter your address");
                }

                else if (phone.equalsIgnoreCase("")) {
                    ph.setError("enter your phone");
                }
                else if (phone.length() != 10) {
                    ph.setError(" numbers  allowed");
                }
                else if (dob.equalsIgnoreCase("")) {
                    D.setError("enter date of birth");
                }
                else if (place.equalsIgnoreCase("")) {
                    p.setError("enter your phone");
                }

                else if (email.equalsIgnoreCase("")) {
                    eml.setError("enter your  email");
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    eml.setError("enter valid email");
                    eml.requestFocus();
                }
                else if (email.matches(("^[a-z]*$"))) {
                    eml.setError(" characters  allowed");
                }
                else if (username.equalsIgnoreCase("")) {
                    u.setError("enter your  username");
                }
                else  if (password.equalsIgnoreCase("")) {
                    pass.setError("enter your  password");
                }
                else if (!username.matches("^[a-z]*$")) {
                    u.setError(" characters  allowed");
                }
                else if (
                        username.length()<= 5) {
                    u.setError(" characters  too short");
                }
                else {
                    uploadBitmap(title);
                }



//

            }
        });
}


ProgressDialog pd;
    private void uploadBitmap(final String title) {
//        pd=new ProgressDialog(ADDPRODUCT.this);
//        pd.setMessage("Uploading....");
//        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
//                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(MainActivity2.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("address", address);
                params.put("gender", gender);
                params.put("phone", phone);
                params.put("place", place);
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);
                params.put("dob", dob);




                params.put("lid",sh.getString("lid",""));

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(PathHolder , filedt ));









                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        PathHolder = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                        filedt = getbyteData(PathHolder);
                        Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        photo.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private byte[] getbyteData(String pathHolder) {
        Log.d("path", pathHolder);
        File fil = new File(pathHolder);
        int fln = (int) fil.length();
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(fil);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[fln];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byteArray = bos.toByteArray();
            inputStream.close();
        } catch (Exception e) {
        }
        return byteArray;



        }
    private void updateLabel(){
        String myFormat="YYYY-MM-dd";
        SimpleDateFormat dateFormat=null;
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
            dateFormat = new SimpleDateFormat(myFormat,Locale.US);

        }
        D.setText(dateFormat.format(myCalendar.getTime()));
    }

}