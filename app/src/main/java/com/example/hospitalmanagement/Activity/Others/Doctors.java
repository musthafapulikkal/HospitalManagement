package com.example.hospitalmanagement.Activity.Others;

import android.content.Context;

public class Doctors {
    private String doc_name;
    private String doc_specl;
    private String doc_holi;
    private String image;
    private String d_time;
    private Context context;
    public Doctors()
    {

    }
    public Doctors(String doc_name, String doc_specl, String doc_holi, String image,String d_time, Context context)
    {
        this.doc_name=doc_name;
        this.doc_specl=doc_specl;
        this.doc_holi=doc_holi;
        this.image=image;
        this.d_time=d_time;
        this.context=context;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_specl() {
        return doc_specl;
    }

    public void setDoc_specl(String doc_specl) {
        this.doc_specl = doc_specl;
    }

    public String getDoc_holi() {
        return doc_holi;
    }

    public void setDoc_holi(String doc_holi) {
        this.doc_holi = doc_holi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getD_time() {
        return d_time;
    }

    public void setD_time(String d_time) {
        this.d_time = d_time;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
