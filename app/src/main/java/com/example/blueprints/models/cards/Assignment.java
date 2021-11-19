package com.example.blueprints.models.cards;

import android.content.res.Resources;

import com.example.blueprints.models.resources.Resource;

import java.util.List;

public abstract class Assignment {

    protected int direction;
    protected int drawable;
    protected List<Resource> listOfResources;

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

    public List<Resource> getListOfResources() {
        return listOfResources;
    }

    public void setListOfResources(List<Resource> listOfResources) {
        this.listOfResources = listOfResources;
    }
    /**@param resource, is the resource that the card will have
     * this method is used to select the  right picture
     * */
    public abstract void initialize(Resource resource);
}
