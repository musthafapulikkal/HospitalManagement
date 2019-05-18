package com.example.hospitalmanagement.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hospitalmanagement.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
   findViewById(R.id.id_admin_view_hos).setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           startActivity(new Intent(getApplicationContext(),ViewapproveHospitalActivity.class));
       }
   });
   findViewById(R.id.id_admin_view_users).setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           startActivity(new Intent(getApplicationContext(),ViewUsersActivity.class));
       }
   });
    }
}
