package com.example.hospitalmanagement.Activity.Others;

import android.content.Context;

public class Users {
    private String email;
    private String name;
    private Context context;
    public Users()
    {

    }
    public Users(String email,String name,Context context)
    {
        this.email=email;
        this.name=name;
        this.context=context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
