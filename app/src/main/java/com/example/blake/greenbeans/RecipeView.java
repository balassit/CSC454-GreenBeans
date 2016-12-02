package com.example.blake.greenbeans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by blake on 11/30/16.
 */

public class RecipeView extends AppCompatActivity {

    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private static final int REQUEST_CODE_CHECK_OUT = 200;
    private static final int REQUEST_CODE_VIEW_RECIPE = 300;
    private static final int REQUEST_CODE_ADD_RECIPE = 400;
    private ArrayList<Meal> mealList;
    private TextView mealTitle;
    private Button btnAddToMeal;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Set Meal title to name of recipe
        mealTitle = (TextView) findViewById(R.id.mealTitle);
        name = getIntent().getStringExtra("name");
        mealList = getIntent().getParcelableArrayListExtra("mealList");
        mealTitle.setText(name);
        System.out.println("_____________________");
        System.out.println(name);
        System.out.println("_____________________");

        //Pass recipe name on add to meal click
        btnAddToMeal = (Button) findViewById(R.id.btnAddToMeal);
        btnAddToMeal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MealCheckout.class);
                if(mealList == null){
                    mealList = new ArrayList<Meal>();
                    Meal meal = new Meal(name);
                    meal.addToQuantity(1);
                    addEquipment(meal);
                    addSkills(meal);
                    mealList.add(meal);
                }
                else if (!getIntent().getParcelableArrayListExtra("mealList").isEmpty()) {
                    boolean added = false;
                    for (int i = 0; i < mealList.size(); i++) {
                        if (mealList.get(i).getRecipe().equals(name)) {
                            mealList.get(i).addToQuantity(1);
                            added = true;
                            break;
                        }
                    }
                    if(!added){
                        Meal meal = new Meal(name);
                        addEquipment(meal);
                        addSkills(meal);
                        meal.addToQuantity(1);
                        mealList.add(meal);
                    }
                }


                intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                startActivityForResult(intent, REQUEST_CODE_ADD_RECIPE);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option, menu);

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                // User chose the "Home" item, go to home
                Intent intent = new Intent();
                intent.putExtra("ActivityResult", getIntent().getStringExtra("name"));
                intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                setResult(Activity.RESULT_OK, intent);
                finish();
                //startActivity(new Intent(RecipeView.this, RecipeList.class));
                return true;

            case R.id.checkout:
                // User chose the "Checkout" action
                //Intent intent2 = new Intent();
                //intent2.putExtra("ActivityResult", getIntent().getStringExtra("name"));
                //setResult(Activity.RESULT_OK, intent2);
                //startActivity(intent2);
                Intent intent2 = new Intent(getApplicationContext(), MealCheckout.class);
                intent2.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                startActivityForResult(intent2, REQUEST_CODE_CHECK_OUT);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Need to load the correct reciepe data based on which item was clicked from the recipe list.
     * the "name" from the data is the name of the recipe. Using this we can display the correct information.
     * Need to call methods from Recipe to set and get recipe data.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_VIEW_RECIPE:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(getApplicationContext(), MealCheckout.class);
                    intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                    mealTitle.setText(data.getStringExtra("name"));
                } else if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }

                break;
            case REQUEST_CODE_CHECK_OUT:
                if (resultCode == RESULT_OK) {
                    Intent intent2 = new Intent(getApplicationContext(), MealCheckout.class);
                    intent2.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                } else if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
        }
    }

    //on add to meal-->update the meal with that number and go back to recipe list? or stay there

    private void addToMeal() {
        btnAddToMeal.setOnClickListener(new View.OnClickListener() {
            //how can i carry the total number of this meal into the page?
            //need to add the current recipe.
            //the recipe view has to use the recipe information
            @Override
            public void onClick(View v) {
                //Meal meal = new Meal(1, )
                Intent intent = new Intent(getApplicationContext(), RecipeList.class);
                startActivityForResult(intent, REQUEST_CODE_RECIPE_LIST);
            }
        });
    }

    public void addEquipment(Meal meal) {
        if(name.equals("Apple")){
            meal.addToEquipment('a');
            meal.addToEquipment('b');
        } else if(name.equals("banana")){
            meal.addToEquipment('b');
        } else if(name.equals("grape")){
            meal.addToEquipment('c');
            meal.addToEquipment('a');
        }
    }

    public void addSkills(Meal meal) {
        if(name.equals("Apple")){
            meal.addToSkills('a');
            meal.addToSkills('b');
        } else if(name.equals("banana")){
            meal.addToSkills('b');
        } else if(name.equals("grape")){
            meal.addToSkills('c');
            meal.addToSkills('a');
        }
    }

}