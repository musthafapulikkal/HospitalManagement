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

public class RegistrationActivity extends AppCompatActivity {
EditText edt_username,edt_password,edt_email,edt_confirmpass,edt_contact;

    Button btn_reg,btn_gotolog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edt_username=(EditText)findViewById(R.id.id_reg_username);
        edt_email=(EditText)findViewById(R.id.id_reg_useremail);
        edt_password=(EditText)findViewById(R.id.id_reg_user_password);
        edt_confirmpass=(EditText)findViewById(R.id.id_reg_user_confirm);
        edt_contact=(EditText)findViewById(R.id.id_reg_usercontact);
        btn_reg=(Button)findViewById(R.id.id_btn_register);
        btn_gotolog=(Button)findViewById(R.id.id_btn_gotologin);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = edt_username.getText().toString().trim();
                final String email = edt_email.getText().toString().trim();
                final String password = edt_password.getText().toString().trim();
                final String confirm = edt_confirmpass.getText().toString().trim();
                final String contact=edt_contact.getText().toString().trim();
                if (TextUtils.isEmpty(username))
                {
                    edt_username.setError("please fill this field");
                    edt_username.requestFocus();
                    return;
                }
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
                if (TextUtils.isEmpty(confirm))
                {
                    edt_confirmpass.setError("please fill this field");
                    edt_confirmpass.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(contact))
                {
                    edt_contact.setError("please fill this field");
                    edt_contact.requestFocus();
                    return;
                }
                if (!password.equals(confirm)) {
                    Toast.makeText(RegistrationActivity.this, "please check your password", Toast.LENGTH_SHORT).show();
                } else {
                    RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
                    String Url = "http://hospitalmanagement.fabstudioz.com/registration.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("response",response);
                            if (response.equals(email))
                            {
//                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("type","user");
                                startActivity(intent);
                                finish();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", username);
                            params.put("email", email);
                            params.put("password", password);
                            params.put("contact",contact);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
        btn_gotolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}
