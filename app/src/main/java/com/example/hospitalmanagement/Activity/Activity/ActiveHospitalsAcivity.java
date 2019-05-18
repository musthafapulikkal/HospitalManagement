package com.example.hospitalmanagement.Activity.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitalmanagement.Activity.Adapter.ActiveHospitalsAdapter;
import com.example.hospitalmanagement.Activity.Adapter.HospitalAdapter;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActiveHospitalsAcivity extends AppCompatActivity {
    private List<Hospital> active_hospitallist=new ArrayList<>();
    private RecyclerView recyclerView;
    private ActiveHospitalsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_hospitals_acivity);
        mAdapter = new ActiveHospitalsAdapter(active_hospitallist);
        recyclerView = (RecyclerView) findViewById(R.id.activehos_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ActiveHospitalsAcivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/activehos.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("active res",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Context context=getApplicationContext();
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String id=jsonObject.optString("hos_id");
                        String name=jsonObject.optString("hos_name");
                        String place=jsonObject.optString("hos_place");
                        String image=jsonObject.optString("image");
                        Hospital hospital=new Hospital(id,name,place,image,context);
                        active_hospitallist.add(hospital);
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
        });
        requestQueue.add(stringRequest);

    }
}
