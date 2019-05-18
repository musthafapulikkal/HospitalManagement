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

public class AddtimeActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter_from;
    ArrayAdapter<CharSequence> adapter_to;
    ArrayAdapter<CharSequence> adapter_from_am;
    ArrayAdapter<CharSequence> adapter_to_am;
    String[] f_time={"--Time--","1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] f_am={"AM","PM"};
    String[] t_time={"--Time--","1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] t_am={"AM","PM"};
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtime);
        final Spinner from_spinner=(Spinner)findViewById(R.id.id_spinner_time_from);
        final Spinner f_am_spinner=(Spinner)findViewById(R.id.id_spinner_time_from_am);
        final Spinner to_spinner=(Spinner)findViewById(R.id.id_spinner_time_to);
        final Spinner t_am_spinner=(Spinner)findViewById(R.id.id_spinner_time_to_am);
        Intent intent=getIntent();
        final String doc_id=intent.getStringExtra("doc_id");
        Toast.makeText(this, "docid"+doc_id, Toast.LENGTH_SHORT).show();
        adapter_from=new ArrayAdapter(this,android.R.layout.simple_spinner_item,f_time);
        adapter_from.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_spinner.setAdapter(adapter_from);
        adapter_from_am=new ArrayAdapter(this,android.R.layout.simple_spinner_item,f_am);
        adapter_from_am.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f_am_spinner.setAdapter(adapter_from_am);

        adapter_to=new ArrayAdapter(this,android.R.layout.simple_spinner_item,t_time);
        adapter_to.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_spinner.setAdapter(adapter_to);

        adapter_to_am=new ArrayAdapter(this,android.R.layout.simple_spinner_item,t_am);
        adapter_to_am.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t_am_spinner.setAdapter(adapter_to_am);
        btn_add=(Button)findViewById(R.id.btn_id_add_av_time);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String from=from_spinner.getSelectedItem().toString();
                final String f_am=f_am_spinner.getSelectedItem().toString();
                final String to=to_spinner.getSelectedItem().toString();
                final String t_am=t_am_spinner.getSelectedItem().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(AddtimeActivity.this);
                String Url="http://hospitalmanagement.fabstudioz.com/addtime.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("time response",response);
                        if (response.equals("success"))
                        {
                            Toast.makeText(AddtimeActivity.this, "saved", Toast.LENGTH_SHORT).show();
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
                        params.put("doc_id",doc_id);
                        params.put("from",from);
                        params.put("f_am",f_am);
                        params.put("to",to);
                        params.put("t_am",t_am);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}
