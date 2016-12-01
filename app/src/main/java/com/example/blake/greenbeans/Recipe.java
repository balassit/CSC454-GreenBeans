package com.example.blake.greenbeans;

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

    public Recipe() {

    }


    public Recipe(String title, String description, String time, ArrayList<String> ingredients, ArrayList<String> equipment, ArrayList<String> directions) {
        this.title = title;
        this.description = description;
        this.time = time;
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
