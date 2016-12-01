package com.example.blake.greenbeans;

import java.util.ArrayList;

/**
 * Created by blake on 11/23/16.
 */

public class RecipeCategory {

    private String name;
    private ArrayList<Recipe> recipeList;

    public RecipeCategory(String name, ArrayList<Recipe> recipeList) {

        this.name = name;
        this.recipeList = recipeList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

}
