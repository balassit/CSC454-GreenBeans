package com.example.blake.greenbeans;

/**
 * Created by blake on 11/30/16.
 */

public class Meal {
    private final int quantity;
    private final Recipe recipe;

    public Meal(int quantity, Recipe recipe) {
        this.quantity = quantity;
        this.recipe = recipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
