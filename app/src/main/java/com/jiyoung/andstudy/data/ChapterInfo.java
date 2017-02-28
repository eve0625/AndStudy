package com.jiyoung.andstudy.data;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class ChapterInfo {

    int imageId;
    String title;
    String description;
    Class mClass;

    public ChapterInfo(int imageId, String title, String description) {
        this(imageId, title, description, null);
    }

    public ChapterInfo(int imageId, String title, String description, Class mclass) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.mClass = mclass;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImage(int imageId) {
        this.imageId = imageId;
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
