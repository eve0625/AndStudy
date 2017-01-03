package com.jiyoung.andstudy.data;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class ChapterInfo {

    Drawable image;
    String title;
    String description;
    Class mClass;

    public ChapterInfo(Drawable image, String title, String description) {
        this(image, title, description, null);
    }

    public ChapterInfo(Drawable image, String title, String description, Class mclass) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.mClass = mclass;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
}
