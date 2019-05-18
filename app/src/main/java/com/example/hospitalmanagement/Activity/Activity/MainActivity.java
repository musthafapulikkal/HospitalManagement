package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
Button btn_login,btn_sign_up;
EditText edt_email,edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sign_up=(Button)findViewById(R.id.btnsignup);
        btn_login=(Button)findViewById(R.id.btnlogin);
        edt_email=(EditText)findViewById(R.id.edtemail);
        edt_password=(EditText)findViewById(R.id.edtpassword);
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        if (type.equals("admin"))
        {
            btn_sign_up.setVisibility(View.GONE);
        }
        else if (type.equals("user"))
        {
            btn_sign_up.setVisibility(View.VISIBLE);
        }
        else
        {

        }
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=edt_email.getText().toString().trim();
                final String password=edt_password.getText().toString().trim();
                if (TextUtils.isEmpty(email))
                {
                    edt_email.setError("fill this field");
                    edt_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    edt_password.setError("fill this field");
                    edt_password.requestFocus();
                    return;
                }
                if (email.equals("admin@gmail.com")&&password.equals("admin"))
                {
                    startActivity(new Intent(getApplicationContext(),AdminHomeActivity.class));
                }
                else
                {
                    userLogin(email,password);
                }
            }
        });

    }

    private void userLogin(final String email, final String password) {
        Log.v("usermail",email);
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String Url="http://hospitalmanagement.fabstudioz.com/login.php?type=user";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("login response",response);
                if (response.equals(email))
                {
                    startActivity(new Intent(getApplicationContext(),UserHomeActivity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
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
        //final String email=edt_email.getText().toString().trim();
        //final String password=edt_password.getText().toString().trim();
    }
}
