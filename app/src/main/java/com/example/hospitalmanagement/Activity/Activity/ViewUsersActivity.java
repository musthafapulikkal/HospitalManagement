package com.example.hospitalmanagement.Activity.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitalmanagement.Activity.Adapter.DoctorAdapter;
import com.example.hospitalmanagement.Activity.Adapter.UserAdapter;
import com.example.hospitalmanagement.Activity.Others.Doctors;
import com.example.hospitalmanagement.Activity.Others.Users;
import com.example.hospitalmanagement.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {
    private List<Users> usersList=new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        mAdapter = new UserAdapter(usersList);
        recyclerView = (RecyclerView) findViewById(R.id.user_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ViewUsersActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/viewUsers.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Context context=getApplicationContext();
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String name=jsonObject.optString("username");
                        String email=jsonObject.getString("useremail");
                        Users users=new Users(email,name,context);
                        usersList.add(users);
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
