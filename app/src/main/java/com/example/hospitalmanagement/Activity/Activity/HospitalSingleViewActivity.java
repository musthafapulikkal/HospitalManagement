package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HospitalSingleViewActivity extends AppCompatActivity {
ImageView imageView;
TextView txt_name;
String lat;
String longt;
String place;
Button btn_doctor,btn_facility,btn_service,btn_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_single_view);

        imageView=(ImageView)findViewById(R.id.img_single_id);
        txt_name=(TextView)findViewById(R.id.id_single_txt_name);
        btn_doctor=(Button)findViewById(R.id.id_single_view_docttors);
        btn_facility=(Button)findViewById(R.id.id_single_view_facility);
        btn_service=(Button)findViewById(R.id.id_single_view_service);
        btn_location=(Button)findViewById(R.id.id_single_view_location);
        Intent intent=getIntent();
        final String hosid=intent.getStringExtra("hosid");
        RequestQueue requestQueue= Volley.newRequestQueue(HospitalSingleViewActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/viewsinglehospital.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("single response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String hosname=jsonObject.optString("hos_name");
                        String image=jsonObject.optString("image");
                        lat=jsonObject.optString("latitude");
                        longt=jsonObject.optString("logtitude");
                        place=jsonObject.optString("hos_place");
                        txt_name.setText(hosname);
                        String Url="http://hospitalmanagement.fabstudioz.com/"+image;
                       Glide.with(getApplicationContext()).load(Url).into(imageView);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("hos_id",hosid);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        btn_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),ViewDoctorsActivity.class);
               i.putExtra("hosid",hosid);
               startActivity(i);
            }
        });
        btn_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewFacilitiesActivity.class);
                i.putExtra("hosid",hosid);
                startActivity(i);
            }
        });
        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewServiceActivity.class);
                i.putExtra("hosid",hosid);
                startActivity(i);
            }
        });
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("abc",lat);
              Intent i=new Intent(getApplicationContext(),ViewLocationActivity.class);
               i.putExtra("lat",lat);
               i.putExtra("longt",longt);
               i.putExtra("place",place);
               startActivity(i);
            }
        });

    }
}
