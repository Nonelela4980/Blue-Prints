package com.example.blueprints.cards;

import android.graphics.drawable.Drawable;

import java.util.List;

public abstract class Assignment {

    protected int direction;
    protected Drawable drawable;
    protected List<Resource> listOfResources;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Drawable getDrawable(int instruction_bank_give_1_brick) {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public List<Resource> getListOfResources() {
        return listOfResources;
    }

    public void setListOfResources(List<Resource> listOfResources) {
        this.listOfResources = listOfResources;
    }
}
