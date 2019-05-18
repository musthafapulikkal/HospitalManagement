package com.example.hospitalmanagement.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitalmanagement.Activity.Adapter.DoctorAdapter;
import com.example.hospitalmanagement.Activity.Adapter.HospitalAdapter;
import com.example.hospitalmanagement.Activity.Others.Doctors;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewDoctorsActivity extends AppCompatActivity {
    private List<Doctors> doctorsList=new ArrayList<>();
    private RecyclerView recyclerView;
    private DoctorAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctors);
        Intent intent=getIntent();
        final String hosid=intent.getStringExtra("hosid");
        Log.v("hos_id",hosid);
        mAdapter = new DoctorAdapter(doctorsList);
        recyclerView = (RecyclerView) findViewById(R.id.doctor_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ViewDoctorsActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/viewdoctors.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("doctor response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Context context=getApplicationContext();
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String name=jsonObject.optString("doc_name");
                        String spcl=jsonObject.optString("doc_specialist");
                        String holiday=jsonObject.optString("doc_holi");
                        String image=jsonObject.optString("image");
                        String d_from=jsonObject.optString("doc_time_from");
                        String d_to=jsonObject.optString("doc_time_to");
                        String doc_time="Time:"+d_from+"to"+d_to;
                        Doctors doctors=new Doctors(name,spcl,holiday,image,doc_time,context);
                        doctorsList.add(doctors);
                        mAdapter.notifyDataSetChanged();
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
    }
}
