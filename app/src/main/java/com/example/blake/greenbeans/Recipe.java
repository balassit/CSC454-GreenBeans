package com.example.blake.greenbeans;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by blake on 11/23/16.
 */

public class Recipe {

    String title;
    String description;
    ArrayList<Ingredient> ingredients;
    ArrayList<String> equipment;
    ArrayList<Direction> directions;
    int rating;
    String notes;
    Image img;

    public Recipe() {
        this.title = null;
        this.description = null;
        this.rating = 0;
        this.notes = null;
        this.img = null;
        this.ingredients = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.directions = new ArrayList<>();
    }

    public Recipe(String title, String description,String notes, int rating, Image img, ArrayList<Ingredient> ingredients, ArrayList<String> equipment, ArrayList<Direction> directions) {
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.rating = rating;
        this.img = img;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.directions = directions;

    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(String Notes) {
        this.notes = notes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDirections(ArrayList<Direction> directions) {
        this.directions = directions;
    }

    public void setEquipment(ArrayList<String> equipment) {

        this.equipment = equipment;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public Image getImg() {
        return img;
    }

    public String getNotes() {

        return notes;
    }

    public int getRating() {
        return rating;
    }


}
