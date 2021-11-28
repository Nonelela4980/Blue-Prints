package com.example.capstone.models.cards;

import com.example.capstone.models.resources.Resource;

import java.util.List;

public abstract class Assignment {

    protected int direction;
    protected int drawable;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    protected int getDrawable(int id) {
        return id;
    }

    public int get_drawable(){return drawable;}

    public void setDrawable(String drawable_Url){

        this.drawable = drawable;
    }
    /**@param resource, is the resource that the card will have
     * this method is used to select the  right picture
     * */
    public abstract void initialize(Resource resource);
}
