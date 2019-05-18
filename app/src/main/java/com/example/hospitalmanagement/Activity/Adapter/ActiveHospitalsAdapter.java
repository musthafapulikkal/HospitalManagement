package com.example.hospitalmanagement.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hospitalmanagement.Activity.Others.Hospital;
import com.example.hospitalmanagement.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveHospitalsAdapter extends RecyclerView.Adapter<ActiveHospitalsAdapter.MyViewHolder> {
    private Context context;
    private List<Hospital> hospitallist;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.active_hos_list,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
     final Hospital hospital=hospitallist.get(i);
     myViewHolder.name.setText(hospital.getHos_name());
     myViewHolder.place.setText(hospital.getPlace());
     String Url="http://hospitalmanagement.fabstudioz.com/"+hospital.getImage();
     Glide.with(hospital.getContext()).load(Url).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return hospitallist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView place;
        Button block;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.imageview_activehos);
            name=(TextView)itemView.findViewById(R.id.txt_activehos_name);
            place=(TextView)itemView.findViewById(R.id.txt_activehos_place);
            block=(Button)itemView.findViewById(R.id.btn_block);
            block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blockHospital(getAdapterPosition());
                }
            });
        }
    }

    private void blockHospital(int adapterPosition) {
        if (hospitallist.size()==0)
        {

        }
        else
        {
            final Hospital hospital=hospitallist.get(adapterPosition);
            Toast.makeText(hospital.getContext(), "blocked", Toast.LENGTH_SHORT).show();
            hospitallist.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(adapterPosition,hospitallist.size());
            RequestQueue requestQueue= Volley.newRequestQueue(hospital.getContext());
            String Url="http://hospitalmanagement.fabstudioz.com/blockhos.php";
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("id",hospital.getHos_id());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public ActiveHospitalsAdapter(List<Hospital> hospitallist){this.hospitallist=hospitallist;}
    public ActiveHospitalsAdapter(Context context){
        this.context=context;
    }
}
