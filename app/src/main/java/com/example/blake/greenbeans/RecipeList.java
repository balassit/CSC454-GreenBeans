package com.example.blake.greenbeans;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by blake on 11/23/16.
 */

public class RecipeList extends AppCompatActivity {

    private ArrayList<String> recipes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        ListView listView = (ListView) findViewById(R.id.listRecipes);
        String[] items = {"Black Bean Hummus", "Mexican Pasta", "French Orange Poached Pears"};
        recipes = new ArrayList<>(Arrays.asList(items));
        Collections.sort(recipes, String.CASE_INSENSITIVE_ORDER);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, recipes);
        listView.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option, menu);

        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                // User chose the "Home" item, go to home
                return true;

            case R.id.checkout:
                // User chose the "Checkout" action
                startActivity(new Intent(RecipeList.this, MealCheckout.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}