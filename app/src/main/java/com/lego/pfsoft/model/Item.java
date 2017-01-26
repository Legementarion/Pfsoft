package com.lego.pfsoft.model;

public class Item {
    private String mName;
    private int mColor;
    private boolean mChecked;

    public Item(String name, int color) {
        this.mName = name;
        this.mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void toggle(){
        mChecked = !mChecked;
    }
}
