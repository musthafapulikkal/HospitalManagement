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
import com.example.hospitalmanagement.Activity.Adapter.FacilityAdpter;
import com.example.hospitalmanagement.Activity.Adapter.ServiceAdapter;
import com.example.hospitalmanagement.Activity.Others.Facility;
import com.example.hospitalmanagement.Activity.Others.Services;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewServiceActivity extends AppCompatActivity {
    private List<Services> servicesList=new ArrayList<>();
    private RecyclerView recyclerView;
    private ServiceAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);
        Intent intent=getIntent();
        final String hosid=intent.getStringExtra("hosid");
        Log.v("hos_id",hosid);
        mAdapter = new ServiceAdapter(servicesList);
        recyclerView = (RecyclerView) findViewById(R.id.service_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ViewServiceActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/viewservices.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Context context=getApplicationContext();
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String caption=jsonObject.optString("caption");
                        String description=jsonObject.optString("description");
                        String image=jsonObject.optString("image");
                        final Services services=new Services(caption,description,image,context);
                        servicesList.add(services);
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
                params.put("hosid",hosid);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
