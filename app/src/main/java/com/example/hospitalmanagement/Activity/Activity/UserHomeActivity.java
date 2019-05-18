package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitalmanagement.R;

import java.util.HashMap;
import java.util.Map;

public class UserHomeActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter_state;
    ArrayAdapter<CharSequence> adapter_dist;
    String[] state={"--state--","Kerala","Tamilnadu"};
    String[] district={"--District--","Trivandrum","Pathanamthitta","Alappuzha","Kottayam","Idukki","Ernakulam","Thrissur","Malappuram","Palakkad","Vayanad","Kollam","Kozhikkod","Kannur","Kasargod"};
    Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        btn_search=(Button)findViewById(R.id.btn_search);
        final Spinner state_spinner=(Spinner)findViewById(R.id.id_spinner_state_user);
        final Spinner dist_spinner=(Spinner)findViewById(R.id.id_spinner_dist_user);
        adapter_state=new ArrayAdapter(this,android.R.layout.simple_spinner_item,state);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(adapter_state);
        adapter_dist=new ArrayAdapter(this,android.R.layout.simple_spinner_item,district);
        adapter_dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dist_spinner.setAdapter(adapter_dist);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String state=state_spinner.getSelectedItem().toString();
                final String dist=dist_spinner.getSelectedItem().toString();
                if (state.equals("--state--"))
                {
                    Toast.makeText(UserHomeActivity.this, "please select a state", Toast.LENGTH_SHORT).show();
                }
                RequestQueue requestQueue= Volley.newRequestQueue(UserHomeActivity.this);
                String Url="http://hospitalmanagement.fabstudioz.com/displayhospital.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("view hos",response);
                        Intent intent=new Intent(getApplicationContext(),ViewHospitalsActivity.class);
                        intent.putExtra("response",response);
                        startActivity(intent);
                        if (response.equals(""))
                        {
                            Toast.makeText(UserHomeActivity.this, "sorry no data found", Toast.LENGTH_SHORT).show();
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
                        params.put("state",state);
                        params.put("district",dist);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}
