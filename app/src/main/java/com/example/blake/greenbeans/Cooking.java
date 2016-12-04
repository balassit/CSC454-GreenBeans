package com.example.blake.greenbeans;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.os.CountDownTimer;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


import java.util.List;

/**
 * Created by blake on 11/23/16.
 */

public class Cooking extends AppCompatActivity {

    private static final int REQUEST_CODE_RECIPE_LIST = 100;
    private static final int REQUEST_CODE_CHECK_OUT = 200;

    private ListView listViewSteps;
    private ArrayList<Meal> mealList;
    private ArrayAdapter<String> adapterSteps;
    private ArrayList<String> stepsList = new ArrayList<String>();
    private ArrayList<List> directionList = new ArrayList<>();
    //private ArrayList<MealItem> mealItems = new ArrayList<>();
    private ArrayList<Step> allSteps = new ArrayList<Step>();

    private Button btnNextStep;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public int counter = 10;

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

        //get the meal list
        //updateMealList();
        addAllSteps();

        /*
        //directions
        for (int i = 0; i < mealList.size(); i++) {
            mealItems.add(setRecipeDirections(mealList.get(i).getRecipe(), mealList.get(i).getQuantity()));
            //directionList.add(temp);
        }

        //print the directions
        for (int i = 0; i > mealItems.size(); i++) {
            stepsList.add(mealItems.get(0).getRecipeName() + ": " + mealItems.get(0).getDirections());
        }
        */

        /*
        adapterSteps = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, stepsList);
        listViewSteps.setAdapter(adapterSteps);
        */

        btnNextStep = (Button) findViewById(R.id.btnNextStep);
        final TextView timer = (TextView) findViewById(R.id.timer);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                counter = 10;
                CountDownTimer timer1 = new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(counter));
                        counter--;

                    }

                    public void onFinish() {
                        timer.setText("FINISHED");
                    }
                };
                timer1.start();
            }
        });

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            }

            public boolean onCreateOptionsMenu(Menu menu) {
                new MenuInflater(this).inflate(R.menu.cancel, menu);

                return (super.onCreateOptionsMenu(menu));
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.cancel:
                        // User chose the "Home" item, go to home
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        setResult(Activity.RESULT_OK, intent);
                        startActivityForResult(intent, REQUEST_CODE_RECIPE_LIST);
                        //startActivity(new Intent(RecipeView.this, RecipeList.class));
                        return true;
                    default:
                        // If we got here, the user's action was not recognized.
                        // Invoke the superclass to handle it.
                        return super.onOptionsItemSelected(item);

                }
            }

            //update Recipe List with new recipe
            private void updateMealList() {
                if (getIntent().getParcelableArrayListExtra("mealList") != null && !getIntent().getParcelableArrayListExtra("mealList").isEmpty()) {
                    mealList = getIntent().getParcelableArrayListExtra("mealList");
                }
            }

            /**
            * add all the steps to the array in order they will go for making the recipe
            */
            private void addAllSteps(){
                String description;
                int time;
                String meal;
                Step temp;

                //NOT SURE IF THIS WILL REFERENCE TEMP AND CHANGE IT EACH TIME
                //BLAKE CARTER LOOK AT THIS
                //ALSO THIS IS HARD CODING HELL hahahahaha

                meal = "Black Bean Hummus";
                description = "first Step Description";
                time = 10;
                temp = new Step(meal, description, time);
                allSteps.add(temp);

                meal = "Mexican Pasta";
                description = "Second Step Description";
                time = 20;
                temp = new Step(meal, description, time);
                allSteps.add(temp);

                meal = "French Orange Poached Pears";
                description = "Thrid Step Description";
                time = 30;
                temp = new Step(meal, description, time);
                allSteps.add(temp);


                meal = "Black Bean Hummus";
                description = "Fourth Step Description";
                time = 40;
                temp = new Step(meal, description, time);
                allSteps.add(temp);

                meal = "Mexican Pasta";
                description = "Fifth Step Description";
                time = 50;
                temp = new Step(meal, description, time);
                allSteps.add(temp);

                meal = "French Orange Poached Pears";
                description = "Sixth Step Description";
                time = 60;
                temp = new Step(meal, description, time);
                allSteps.add(temp);

            }


            /**
             * Set the directions for an individual recipe(meal item).
             *
             * @param recipeName
             * @param quantity
             */
            /*

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
                } else if (recipeName.equals("banana")) {
                    meal = new MealItem();
                    meal.addDirections("get banana");
                    meal.addDirections("peel banana");
                    meal.addDirections("put banana in bowl");
                    meal.setQuantity(quantity);
                    meal.setRecipeName(recipeName);

                } else if (recipeName.equals("grape")) {
                    meal = new MealItem();
                    meal.addDirections("pick grape");
                    meal.setQuantity(quantity);
                    meal.setRecipeName(recipeName);
                } else {
                    System.out.println("WRONG RECIPE ENTERED FIX IT");
                }

                return meal;
            }
            */

            /**
             * ATTENTION: This was auto-generated to implement the App Indexing API.
             * See https://g.co/AppIndexing/AndroidStudio for more information.
             */
            public Action getIndexApiAction() {
                Thing object = new Thing.Builder()
                        .setName("Cooking Page") // TODO: Define a title for the content shown.
                        // TODO: Make sure this auto-generated URL is correct.
                        .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                        .build();
                return new Action.Builder(Action.TYPE_VIEW)
                        .setObject(object)
                        .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                        .build();
            }

            @Override
            public void onStart() {
                super.onStart();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client.connect();
                AppIndex.AppIndexApi.start(client, getIndexApiAction());
            }

            @Override
            public void onStop() {
                super.onStop();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                AppIndex.AppIndexApi.end(client, getIndexApiAction());
                client.disconnect();
            }
    /*
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
    */


}