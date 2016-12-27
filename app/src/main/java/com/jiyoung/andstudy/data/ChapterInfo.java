package com.jiyoung.andstudy.data;

import android.graphics.drawable.Drawable;

public class ChapterInfo {

    Drawable image;
    String title;
    String description;

    public ChapterInfo(Drawable image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
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
}
