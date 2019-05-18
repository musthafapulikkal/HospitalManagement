package com.example.hospitalmanagement.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hospitalmanagement.Activity.Others.Doctors;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {
    private Context context;
    private List<Doctors> doctorsList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctors_list,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Doctors doctors=doctorsList.get(i);
        myViewHolder.name.setText(doctors.getDoc_name());
        //Log.v("docnamee",doctors.getDoc_name());
      myViewHolder.holiday.setText("holiday:"+doctors.getDoc_holi());
      myViewHolder.specialist.setText(doctors.getDoc_specl());
        String Url="http://hospitalmanagement.fabstudioz.com/"+doctors.getImage();
       Glide.with(doctors.getContext()).load(Url).apply(new RequestOptions().circleCropTransform()).into(myViewHolder.imageView);
       myViewHolder.d_time.setText(doctors.getD_time());
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,holiday,specialist,d_time;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.doctor_name);
            specialist=(TextView)itemView.findViewById(R.id.doctor_specl);
            holiday=(TextView)itemView.findViewById(R.id.doctor_holiday);
            imageView=(ImageView)itemView.findViewById(R.id.img_doctor);
            d_time=(TextView)itemView.findViewById(R.id.doctor_time);
        }
    }
    public DoctorAdapter(List<Doctors> doctorsList){this.doctorsList=doctorsList;}
    public DoctorAdapter(Context context){
        this.context=context;
    }
}
