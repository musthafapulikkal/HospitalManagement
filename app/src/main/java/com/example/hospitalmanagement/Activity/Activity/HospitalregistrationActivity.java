package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hospitalmanagement.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class HospitalregistrationActivity extends AppCompatActivity {
   EditText edt_hosregname,edt_hosregmail,edt_hosregplace,edt_hosregcontact,edt_hoeregpassword;
    ArrayAdapter<CharSequence> adapter_state;
    ArrayAdapter<CharSequence> adapter_dist;
    ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    Button btn_choose,btn_register;
    String[] state={"Kerala","Tamilnadu"};
    String[] district={"Trivandrum","Pathanamthitta","Alappuzha","Kottayam","Idukki","Ernakulam","Thrissur","Malappuram","Palakkad","Vayanad","Kollam","Kozhikkod","Kannur","Kasargod"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalregistration);
        edt_hosregname=(EditText)findViewById(R.id.id_hos_regname);
        edt_hosregplace=(EditText)findViewById(R.id.id_hos_regplace);
        edt_hosregmail=(EditText)findViewById(R.id.id_hos_regemail);
        edt_hosregcontact=(EditText)findViewById(R.id.id_hos_regcontact);
        edt_hoeregpassword=(EditText)findViewById(R.id.id_hos_regpass);
        imageView=(ImageView)findViewById(R.id.id_hos_image);
        btn_choose=(Button)findViewById(R.id.btn_choose_hos);
        btn_register=(Button)findViewById(R.id.btn_register);
        final Spinner state_spinner=(Spinner)findViewById(R.id.id_spinner_state);
        final Spinner dist_spinner=(Spinner)findViewById(R.id.id_spinner_dist);
        adapter_state=new ArrayAdapter(this,android.R.layout.simple_spinner_item,state);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(adapter_state);
        adapter_dist=new ArrayAdapter(this,android.R.layout.simple_spinner_item,district);
        adapter_dist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dist_spinner.setAdapter(adapter_dist);
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String state=state_spinner.getSelectedItem().toString();
                final String dist=dist_spinner.getSelectedItem().toString();
                final String name=edt_hosregname.getText().toString().trim();
                final String place=edt_hosregplace.getText().toString().trim();
                final String contact=edt_hosregcontact.getText().toString().trim();
                final String email=edt_hosregmail.getText().toString().trim();
                final String password=edt_hoeregpassword.getText().toString().trim();
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] imageInByte=baos.toByteArray();
                final String image= Base64.encodeToString(imageInByte,Base64.DEFAULT);
                RequestQueue requestQueue=Volley.newRequestQueue(HospitalregistrationActivity.this);
                String Url="http://hospitalmanagement.fabstudioz.com/hos_registration.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    Log.v("responseeee",response);
                    if (response.equals("success"))
                    {
                        startActivity(new Intent(getApplicationContext(),HospitalLoginActivity.class));
                        finish();
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
                        params.put("name",name);
                        params.put("email",email);
                        params.put("place",place);
                        params.put("state",state);
                        params.put("district",dist);
                        params.put("contact",contact);
                        params.put("password",password);
                        params.put("image",image);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null != data){
            Uri selectedImage=data.getData();
            String[] filepathColumn={MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filepathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filepathColumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }
    }
}
