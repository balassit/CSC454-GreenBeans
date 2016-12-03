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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by blake on 11/23/16.
 */

public class MealCheckout extends AppCompatActivity {


    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private static final int REQUEST_CODE_CHECKOUT = 500;

    private ListView listViewRecipe;
    private ListView listViewEquipment;
    private ListView listViewSkills;
    private ArrayAdapter<String> adapterRecipe;
    private ArrayAdapter<String> adapterEquipment;
    private ArrayAdapter<String> adapterSkills;
    private ArrayList<String> recipeList = new ArrayList<String>();
    private ArrayList<Meal> mealList;
    private ArrayList<String> equipmentList = new ArrayList<String>();
    private ArrayList<String> skillsList = new ArrayList<String>();
    private Button btnCheckout;
    private ArrayList<Ingredient> ingredientList;

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
        listViewRecipe = (ListView) findViewById(R.id.meal);
        listViewEquipment = (ListView) findViewById(R.id.equipmentList);
        listViewSkills = (ListView) findViewById(R.id.skillsList);


        updateRecipeList();

        //Create list of Recipes
        adapterRecipe = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, recipeList);
        adapterEquipment = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, equipmentList);
        adapterSkills = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, skillsList);
        listViewRecipe.setAdapter(adapterRecipe);
        listViewEquipment.setAdapter(adapterEquipment);
        listViewSkills.setAdapter(adapterSkills);

        ingredientList = getIntent().getParcelableArrayListExtra("ingredientList");
        //Pass recipe name on add to meal click
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cooking.class);
                intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                intent.putParcelableArrayListExtra("ingredientList", (ArrayList<? extends Parcelable>) ingredientList);
                startActivityForResult(intent, REQUEST_CODE_CHECKOUT);


            }
        });
    }

    //update Recipe List with new recipe
    private void updateRecipeList(){
            if (getIntent().getParcelableArrayListExtra("mealList") != null && !getIntent().getParcelableArrayListExtra("mealList").isEmpty()) {
            mealList = getIntent().getParcelableArrayListExtra("mealList");
            for(int i = 0; i < mealList.size(); i++){
                recipeList.add(mealList.get(i).getRecipe());
                getEquipmentList(mealList.get(i).getEquipment());
                getSkillsList(mealList.get(i).getSkills());
            }
            Collections.sort(recipeList, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(equipmentList, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(skillsList, String.CASE_INSENSITIVE_ORDER);
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
                getIntent().getParcelableArrayListExtra("ingredientList");
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

    private void getEquipmentList(String equipment) {
        if (equipment == null) {
            return;
        }
        char[] list = equipment.toCharArray();

        for(int i = 0; i < list.length; i++) {
            String toAdd = null;
            switch (list[i]) {
                case 'a':
                    toAdd = "food processor";
                    break;
                case 'b':
                    toAdd = "spatula";
                    break;
                case 'c':
                    toAdd = "stove";
                    break;
                case 'd':
                    toAdd = "large skillet";
                    break;
                case 'e':
                    toAdd = "large pot";
                    break;
                case 'f':
                    toAdd = "wooden spoon";
                    break;
                case 'g':
                    toAdd = "large saucepan";
                    break;
                case 'h':
                    toAdd = "large saucepan cover";
                    break;
                default:
                    toAdd = null;
                    break;
            }
            if (toAdd != null && !equipmentList.contains(toAdd)){
                equipmentList.add(toAdd);
            }
        }
    }

    private void getSkillsList(String skills) {
        if (skills == null) {
            return;
        }
        char[] list = skills.toCharArray();

        for(int i = 0; i < list.length; i++) {
            String toAdd = null;
            switch (list[i]) {
                case 'a':
                    toAdd = "Straining and keeping liquid";
                    break;
                case 'b':
                    toAdd = "Cooking pasta";
                    break;
                case 'c':
                    toAdd = "Straining pasta";
                    break;
                case 'd':
                    toAdd = "Sautéing vegetables";
                    break;
                case 'e':
                    toAdd = "Poaching fruit";
                    break;
                default:
                    toAdd = null;
                    break;
            }
            if (toAdd != null && !skillsList.contains(toAdd)) {
                skillsList.add(toAdd);
            }
        }
    }
}