package com.example.hospitalmanagement.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

public class HospitalLoginActivity extends AppCompatActivity {
EditText edt_email,edt_password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "namelKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);
        edt_email=(EditText)findViewById(R.id.edt_hos_email);
        edt_password=(EditText)findViewById(R.id.edt_hos_password);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        findViewById(R.id.btn_hos_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final String email=edt_email.getText().toString().trim();
            final String password=edt_password.getText().toString().trim();
            if (TextUtils.isEmpty(email))
            {
                edt_email.setError("please fill this field");
                edt_email.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                edt_password.setError("please fill this field");
                edt_password.requestFocus();
                return;
            }
                RequestQueue requestQueue= Volley.newRequestQueue(HospitalLoginActivity.this);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(name,email);
                editor.apply();
                String Url="http://hospitalmanagement.fabstudioz.com/login.php?type=hospital";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response log",response);
                        if (response.equals(email))
                        {
                            startActivity(new Intent(getApplicationContext(),HospitalHomeActivity.class));
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
                        params.put("email",email);
                        params.put("password",password);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });


        findViewById(R.id.btn_hosgotosignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HospitalregistrationActivity.class));
            }
        });
    }
}
