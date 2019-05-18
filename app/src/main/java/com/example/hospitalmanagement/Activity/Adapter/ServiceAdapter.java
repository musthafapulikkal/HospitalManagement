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
import com.example.hospitalmanagement.Activity.Others.Facility;
import com.example.hospitalmanagement.Activity.Others.Services;
import com.example.hospitalmanagement.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyviewHolder> {
    private Context context;
    private List<Services> servicesList;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_list,viewGroup,false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        final Services services=servicesList.get(i);
        myviewHolder.txt_cap.setText(services.getCaption());
        myviewHolder.txt_des.setText(services.getDescription());
        String Url="http://hospitalmanagement.fabstudioz.com/"+services.getImage();
        Glide.with(services.getContext()).load(Url).into(myviewHolder.image);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txt_cap;
        TextView txt_des;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.id_img_service);
            txt_cap=(TextView)itemView.findViewById(R.id.id_txt_service_cap);
            txt_des=(TextView)itemView.findViewById(R.id.id_txt_service_des);

        }
    }
    public ServiceAdapter(List<Services> servicesList){this.servicesList=servicesList;}
    public ServiceAdapter(Context context){
        this.context=context;
    }
}
