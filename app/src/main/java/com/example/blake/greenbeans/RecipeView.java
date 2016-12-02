package com.example.blake.greenbeans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by blake on 11/30/16.
 */

public class RecipeView extends AppCompatActivity {

    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private static final int REQUEST_CODE_CHECK_OUT = 200;
    private static final int REQUEST_CODE_VIEW_RECIPE = 300;
    private TextView nameView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
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
                startActivity(new Intent(RecipeView.this, RecipeList.class));
                //finish();
                return true;

            case R.id.checkout:
                // User chose the "Checkout" action
                Intent intent = new Intent(getApplicationContext(), MealCheckout.class);
                startActivityForResult(intent, REQUEST_CODE_CHECK_OUT);
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
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_VIEW_RECIPE:
                if (resultCode == RESULT_OK) {
                    nameView.setText(data.getStringExtra("name"));
                } else if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }

                break;
            case REQUEST_CODE_CHECK_OUT:
                if (resultCode == RESULT_OK) {
                    //GO TO CHECKOUT?
                } else if (resultCode == RESULT_CANCELED) {
                    //Write your code if there's no result
                }
        }
    }

    //on add to meal-->update the meal with that number and go back to recipe list? or stay there

    private void addToMeal() {
        button.setOnClickListener(new View.OnClickListener() {
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

    private void setRecipeViewData() {
        Recipe r = new Recipe();
        r.getDescription();
        r.getDirections();
        r.getEquipment();
        r.getImg();
        r.getIngredients();
        r.getNotes();
        r.getRating();
        r.getTitle();
    }

    private void setRecipes() {
        Recipe chicken = new Recipe();
        Recipe carrots = new Recipe();
        Recipe milk = new Recipe();
        Meal meal = new Meal(1, chicken);
    }

}