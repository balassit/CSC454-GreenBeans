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

/**
 * Created by blake on 11/23/16.
 */

public class MealCheckout extends AppCompatActivity {

    String ActivityResult;
    private static final int REQUEST_CODE_RECIPE_LIST = 100;
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
            Collections.sort(recipeList);
            Collections.sort(equipmentList);
            Collections.sort(skillsList);
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

    private void getEquipmentList(String equipment){
        if (equipment == null) {
            return;
        }
        char[] list = equipment.toCharArray();

        for(int i = 0; i < list.length; i++) {
            if(list[i] == 'a') {
                if(!equipmentList.contains("Stove")){
                    equipmentList.add("Stove");
                }
            } else if(list[i] == 'b') {
                if(!equipmentList.contains("Oven")){
                    equipmentList.add("Oven");
                }
            } else if(list[i] == 'c') {
                if(!equipmentList.contains("Pot")){
                    equipmentList.add("Pot");
                }
            }
        }
    }

    private void getSkillsList(String skills){
        if (skills == null) {
            return;
        }
        char[] list = skills.toCharArray();

        for(int i = 0; i < list.length; i++) {
            if(list[i] == 'a') {
                if(!skillsList.contains("Stir")){
                    skillsList.add("Stir");
                }
            } else if(list[i] == 'b') {
                if(!skillsList.contains("mix")){
                    skillsList.add("mix");
                }
            } else if(list[i] == 'c') {
                if(!skillsList.contains("cut")){
                    skillsList.add("cut");
                }
            }
        }
    }
}