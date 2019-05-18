package com.example.hospitalmanagement.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hospitalmanagement.Activity.Others.Doctors;
import com.example.hospitalmanagement.Activity.Others.Users;
import com.example.hospitalmanagement.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<Users> usersList;
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
      final Users users=usersList.get(i);
      myViewHolder.email.setText(users.getEmail());
      myViewHolder.name.setText(users.getName());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email,name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email=(TextView)itemView.findViewById(R.id.user_email);
            name=(TextView) itemView.findViewById(R.id.user_name);

        }
    }
    public UserAdapter(List<Users> usersList){this.usersList=usersList;}
    public UserAdapter(Context context){
        this.context=context;
    }
}
