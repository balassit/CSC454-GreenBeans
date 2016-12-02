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

/**
 * Created by blake on 11/23/16.
 */

public class MealCheckout extends AppCompatActivity {

    String ActivityResult;
    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> recipeList = new ArrayList<String>();
    private ArrayList<Meal> mealList = new ArrayList<Meal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_checkout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listView = (ListView) findViewById(R.id.meal);


        updateRecipeList();

        //Create list of Recipes
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, recipeList);
        listView.setAdapter(adapter);
    }

    //update Recipe List with new recipe
    private void updateRecipeList(){
        if (getIntent().getParcelableArrayListExtra("mealList") != null && !getIntent().getParcelableArrayListExtra("mealList").isEmpty()) {
            ArrayList<Meal> temp = getIntent().getParcelableArrayListExtra("mealList");
            for(int i = 0; i < temp.size(); i++){
                //duplicates allowed
                mealList.add(temp.get(i));
                recipeList.add(temp.get(i).getRecipe());
            }
            //adapter.add(addedRecipe);
        } else {
            System.out.println("_________________");
            System.out.println("NO ITEMS");
            System.out.println("_________________");
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
                //Intent intent = new Intent();
                //intent.putExtra("ActivityResult", getIntent().getStringExtra("name"));
                //setResult(Activity.RESULT_OK, intent);
               //finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                startActivityForResult(intent, REQUEST_CODE_RECIPE_LIST);
                return true;


            case R.id.checkout:
                // User chose the "Checkout" action
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_RECIPE_LIST:
                if (resultCode == RESULT_OK) {
                    //GO TO RECIPE LIST?
                    //finish?
                }
                else if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;
        }
    }

}