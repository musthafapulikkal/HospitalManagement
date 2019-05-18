package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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

import static com.example.hospitalmanagement.Activity.Activity.HospitalLoginActivity.MyPREFERENCES;
import static com.example.hospitalmanagement.Activity.Activity.HospitalLoginActivity.name;

public class AddServicesActivity extends AppCompatActivity {
EditText edt_caption,edt_description;
Button btn_add;
ImageButton btn_choose;
ImageView imageView;
private static int RESULT_LOAD_IMAGE=1;
private Bitmap bitmap;
private String imagepath=null;
String hos_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);
        edt_caption=(EditText)findViewById(R.id.id_serv_caption);
        edt_description=(EditText)findViewById(R.id.id_serv_description);
        btn_add=(Button)findViewById(R.id.id_btn_add_service);
        imageView=(ImageView)findViewById(R.id.id_imageview_services);
        btn_choose=(ImageButton) findViewById(R.id.id_btn_choose_service);
        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        hos_email=sharedPreferences.getString(name,null);
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String caption=edt_caption.getText().toString().trim();
                final String description=edt_description.getText().toString().trim();
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] imageInByte=baos.toByteArray();
                final String image= Base64.encodeToString(imageInByte,Base64.DEFAULT);
                if (TextUtils.isEmpty(caption))
                {
                    edt_caption.setError("please fill this field");
                    edt_caption.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(description))
                {
                    edt_description.setError("please fill this fiels");
                    edt_description.requestFocus();
                    return;
                }
                RequestQueue requestQueue= Volley.newRequestQueue(AddServicesActivity.this);
                String Url="http://hospitalmanagement.fabstudioz.com/addservice.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("service response",response);
                        if (response.equals("success"))
                        {
                            startActivity(new Intent(getApplicationContext(),HospitalHomeActivity.class));
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
                        params.put("service_caption",caption);
                        params.put("service_description",description);
                        params.put("hos_email",hos_email);
                        params.put("service_image",image);
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
