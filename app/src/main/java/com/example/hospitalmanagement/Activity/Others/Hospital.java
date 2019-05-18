package com.example.hospitalmanagement.Activity.Others;

import android.content.Context;

public class Hospital {
private String hos_id;
private String hos_name;
private String place;
private String image;
private Context context;
public Hospital()
{

}
public Hospital(String hos_id,String hos_name,String hos_place,String image,Context context)
{
    this.hos_id=hos_id;
    this.hos_name=hos_name;
    this.place=hos_place;
    this.image=image;
    this.context=context;
}

    public String getHos_id() {
        return hos_id;
    }

    public void setHos_id(String hos_id) {
        this.hos_id = hos_id;
    }

    public String getHos_name() {
        return hos_name;
    }

    public void setHos_name(String hos_name) {
        this.hos_name = hos_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
