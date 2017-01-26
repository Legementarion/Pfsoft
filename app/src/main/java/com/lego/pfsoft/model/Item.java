package com.lego.pfsoft.model;

public class Item {
    private String mName;
    private String mColor;

    public Item(String name, String color) {
        this.mName = name;
        this.mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }
}
