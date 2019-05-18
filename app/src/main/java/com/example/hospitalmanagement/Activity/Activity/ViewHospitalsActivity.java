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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.hospitalmanagement.Activity.Adapter.HospitalAdapter;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.Activity.Others.RecyclerTouchListner;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewHospitalsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<Hospital> hospitallist=new ArrayList<>();
    private RecyclerView recyclerView;
    private HospitalAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospitals);
        mAdapter = new HospitalAdapter(hospitallist);
        recyclerView = (RecyclerView) findViewById(R.id.hos_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListner(getApplicationContext(), recyclerView, new RecyclerTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Hospital hospital=hospitallist.get(position);
                final  String hosid=hospital.getHos_id();
               Intent intent=new Intent(getApplicationContext(),HospitalSingleViewActivity.class);
               intent.putExtra("hosid",hosid);
               startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        Intent intent=getIntent();
        Context context=getApplicationContext();
        String response=intent.getStringExtra("response");
        try {
            JSONArray jsonArray=new JSONArray(response);
            for (int i=0;i<=jsonArray.length();i++)
            {
                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                String id=jsonObject.optString("hos_id");
                Log.v("id",id);
                String name=jsonObject.optString("hos_name");
                Log.v("name",name);
                String place=jsonObject.optString("hos_place");
                Log.v("place",place);
                String image=jsonObject.optString("image");
                Hospital hospital=new Hospital(id,name,place,image,context);
                hospitallist.add(hospital);
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
