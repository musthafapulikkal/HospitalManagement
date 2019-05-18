package com.example.hospitalmanagement.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hospitalmanagement.Activity.Others.Doctors;
import com.example.hospitalmanagement.Activity.Others.Facility;
import com.example.hospitalmanagement.R;

import java.util.List;

public class FacilityAdpter extends RecyclerView.Adapter<FacilityAdpter.MyviewHolder> {
    private Context context;
    private List<Facility> facilityList;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faclity_list,viewGroup,false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
     final Facility facility=facilityList.get(i);
     myviewHolder.caption.setText(facility.getCaption());
     myviewHolder.description.setText(facility.getDescription());
        String Url="http://hospitalmanagement.fabstudioz.com/"+facility.getImage();
        Glide.with(facility.getContext()).load(Url).into(myviewHolder.image);
    }

    @Override
    public int getItemCount() {
        return facilityList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView caption,description;
        ImageView image;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            caption=(TextView)itemView.findViewById(R.id.id_txt_facility_cap);
            description=(TextView)itemView.findViewById(R.id.id_txt_facility_des);
            image=(ImageView)itemView.findViewById(R.id.id_img_facility);
        }
    }
    public FacilityAdpter(List<Facility> facilityList){this.facilityList=facilityList;}
    public FacilityAdpter(Context context){
        this.context=context;
    }
}
