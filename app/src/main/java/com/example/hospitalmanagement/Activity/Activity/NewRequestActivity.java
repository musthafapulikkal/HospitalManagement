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
import com.example.hospitalmanagement.Activity.Adapter.NewRequestAdapter;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewRequestActivity extends AppCompatActivity {
    private List<Hospital> new_hospitallist=new ArrayList<>();
    private RecyclerView recyclerView;
    private NewRequestAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        mAdapter = new NewRequestAdapter(new_hospitallist);
        recyclerView = (RecyclerView) findViewById(R.id.newreq_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(NewRequestActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/newrequest.php";
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("new req",response);
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
                        new_hospitallist.add(hospital);
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
