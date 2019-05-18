package com.example.hospitalmanagement.Activity.Others;

import android.content.Context;

public class Facility {

    private String caption;
    private String description;
    private String image;
    private Context context;
    public Facility()
    {

    }
    public Facility(String caption,String description,String image,Context context)
    {
        //this.name=name;
        this.caption=caption;
        this.description=description;
        this.image=image;
        this.context=context;
    }
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
