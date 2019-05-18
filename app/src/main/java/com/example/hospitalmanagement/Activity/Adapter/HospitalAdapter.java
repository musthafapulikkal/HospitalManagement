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
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyviewHolder> {
    public interface RecyclerViewClickListener{
        public void recyclerViewListClicked(View v, int position);
    }
    private Context context;
    private List<Hospital> hospitallist;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hospital_list,viewGroup,false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        final Hospital hospital=hospitallist.get(i);
        myviewHolder.name.setText(hospital.getHos_name());
        Log.v("nnname",hospital.getHos_name());
        myviewHolder.place.setText(hospital.getPlace());
        String Url="http://hospitalmanagement.fabstudioz.com/"+hospital.getImage();
        Glide.with(hospital.getContext()).load(Url).into(myviewHolder.hosimage);
    }

    @Override
    public int getItemCount() {
        return hospitallist.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView hosimage;
        TextView name,place;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txt_hos_name);
            place=(TextView)itemView.findViewById(R.id.txt_hos_place);
            hosimage=(ImageView)itemView.findViewById(R.id.imageview_hos);
        }
    }
    public HospitalAdapter(List<Hospital> hospitallist){this.hospitallist=hospitallist;}
    public HospitalAdapter(Context context){
        this.context=context;
    }
}
