package com.example.blake.greenbeans;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by hal on 12/4/2016.
 */

public class Step {

    int time;
    String description;
    String meal;

    public Step(String meal, String description, int time){
        this.meal = meal;
        this.description = description;
        this.time = time;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


}
