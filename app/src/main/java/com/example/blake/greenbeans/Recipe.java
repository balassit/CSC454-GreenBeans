package com.example.blake.greenbeans;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by blake on 11/23/16.
 */

public class Recipe {

    String title;
    String description;
    String time;
    ArrayList<String> ingredients;
    ArrayList<String> equipment;
    ArrayList<String> directions;
    int rating;
    String notes;
    Image img;

    public Recipe() {
        this.title = null;
        this.description = null;
        this.time = null;
        this.rating = 0;
        this.notes = null;
        this.img = null;
        this.ingredients = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.directions = new ArrayList<>();
    }

    public Recipe(String title, String description, String time, String notes, int rating, Image img, ArrayList<String> ingredients, ArrayList<String> equipment, ArrayList<String> directions) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.notes = notes;
        this.rating = rating;
        this.img = img;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.directions = directions;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

}
