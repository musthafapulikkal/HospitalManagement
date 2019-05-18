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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

public class AddDoctorsActivity extends AppCompatActivity {
    EditText edt_docname,edt_docspciality,edt_docholiday;
    Button btn_save,btn_time;
    ImageView imageView;
    ImageButton imageButton;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    String hos_email;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctors);
        edt_docname=(EditText)findViewById(R.id.id_doc_name);
        edt_docspciality=(EditText)findViewById(R.id.id_doc_specialist);
        edt_docholiday=(EditText)findViewById(R.id.id_doc_holi_day);
        btn_save=(Button)findViewById(R.id.id_save_doc_data);
        btn_time=(Button)findViewById(R.id.id_doc_time_btn);
        imageButton=(ImageButton)findViewById(R.id.choose_doc_pro_pic_btn);
        imageView=(ImageView)findViewById(R.id.docimg);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        hos_email=shared.getString(name,null);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String doctor_name=edt_docname.getText().toString().trim();
                final String doctor_spcl=edt_docspciality.getText().toString().trim();
                final String doctor_holiday=edt_docholiday.getText().toString().trim();

                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] imageInByte=baos.toByteArray();
                final String image= Base64.encodeToString(imageInByte,Base64.DEFAULT);
                RequestQueue requestQueue= Volley.newRequestQueue(AddDoctorsActivity.this);
                String Url="http://hospitalmanagement.fabstudioz.com/adddoctors.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("doctor response",response);
                        String[] seperated=response.split(":");
                        id=seperated[1];
                        Log.v("id",seperated[1]);
                        if (seperated[0].equals("success"))
                        {
                            Toast.makeText(AddDoctorsActivity.this, "data saved please add time", Toast.LENGTH_SHORT).show();
                            edt_docname.getText().clear();
                            edt_docholiday.getText().clear();
                            edt_docspciality.getText().clear();
                            Intent intent=new Intent(getApplicationContext(),AddtimeActivity.class);
                            intent.putExtra("doc_id",id);
                            startActivity(intent);

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
                        params.put("hosemail",hos_email);
                        params.put("docname",doctor_name);
                        params.put("docspcl",doctor_spcl);
                        params.put("docholi",doctor_holiday);
                        params.put("docimg",image);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddtimeActivity.class));
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
