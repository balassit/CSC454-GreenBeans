package com.example.blake.greenbeans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by blake on 11/23/16.
 */

public class Cooking extends AppCompatActivity{

    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private ListView listViewSteps;
    private ArrayList<Meal> mealList;
    private ArrayAdapter<String> adapterSteps;
    private ArrayList<String> stepsList = new ArrayList<String>();
    private ArrayList<List> directionList = new ArrayList<>();
    private ArrayList<MealItem> mealItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooking);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listViewSteps = (ListView) findViewById(R.id.stepsList);

        //get the meal list
        updateMealList();

        //directions
        for(int i = 0; i < mealList.size(); i++) {
            mealItems.add(setRecipeDirections(mealList.get(i).getRecipe(), mealList.get(i).getQuantity()));
            //directionList.add(temp);
        }

        //print the directions
        for(int i = 0; i > mealItems.size(); i++) {
            stepsList.add(mealItems.get(0).getRecipeName() + ": " + mealItems.get(0).getDirections());
        }

        adapterSteps = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, stepsList);
        listViewSteps.setAdapter(adapterSteps);
    }

    //update Recipe List with new recipe
    private void updateMealList() {
        if (getIntent().getParcelableArrayListExtra("mealList") != null && !getIntent().getParcelableArrayListExtra("mealList").isEmpty()) {
            mealList = getIntent().getParcelableArrayListExtra("mealList");
        }
    }


    /**
     * Set the directions for an individual recipe(meal item).
     * @param recipeName
     * @param quantity
     */
    private MealItem setRecipeDirections(String recipeName, int quantity) {
        MealItem meal = new MealItem();
        if (recipeName.equals("Apple")) {
            meal = new MealItem();
            meal.addDirections("get apple");
            meal.addDirections("wash apple");
            meal.addDirections("cut apple");
            meal.addDirections("put apple on plate");
            meal.setQuantity(quantity);
            meal.setRecipeName(recipeName);
        }
        else if(recipeName.equals("banana")) {
            meal = new MealItem();
            meal.addDirections("get banana");
            meal.addDirections("peel banana");
            meal.addDirections("put banana in bowl");
            meal.setQuantity(quantity);
            meal.setRecipeName(recipeName);

        }
        else if(recipeName.equals("grape")) {
            meal = new MealItem();
            meal.addDirections("pick grape");
            meal.setQuantity(quantity);
            meal.setRecipeName(recipeName);
        } else {
            System.out.println("WRONG RECIPE ENTERED FIX IT");
        }

        return meal;
    }

    private class MealItem {
        ArrayList<String> directions;
        String recipeName;
        int quantity;

        public MealItem() {
            this.directions = new ArrayList<>();
            this.recipeName = null;
            this.quantity = 0;
        }



        public int getQuantity() {
            return quantity;
        }

        public String getRecipeName() {
            return recipeName;
        }

        public ArrayList<String> getDirections() {
            return directions;
        }

        public void addDirections(String direction) {
            directions.add(direction);
        }

        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



    }
}
