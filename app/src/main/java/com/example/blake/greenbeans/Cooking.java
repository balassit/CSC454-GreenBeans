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

    private TextView timer;
    private TextView previousStep;
    private TextView currentStep;
    private TextView nextStep;
    private TextView currentStepTitle;
    private TextView nextStepTitle;
    private Button btnNextStep;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        timer = (TextView) findViewById(R.id.timer);
        previousStep = (TextView) findViewById(R.id.previous_step);
        currentStep = (TextView) findViewById(R.id.current_step);
        nextStep = (TextView) findViewById(R.id.next_step);
        btnNextStep = (Button) findViewById(R.id.btnNextStep);
        currentStepTitle = (TextView) findViewById(R.id.current);
        nextStepTitle = (TextView) findViewById(R.id.next);

        //set previous step to nothing
        //previousStep.setText();
        //set current step to first step
        currentStep.setText(allSteps.get(0).getMeal() + allSteps.get(0).getDescription());
        //set next step to next step
        nextStep.setText(allSteps.get(1).getMeal() + allSteps.get(1).getDescription());
        //set timer initial value
        timer.setText("00:00");

        //sets up the button and starts cooking
        startCooking();


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

    private int i = 0;
    private int counter = 10;
    private CountDownTimer timer1;
    private boolean finish = false;

    private void startCooking() {
        btnNextStep = (Button) findViewById(R.id.btnNextStep);

        if (finish) {
            cook();
        } else {
            btnNextStep.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    cook();
                }
            });
        }
    }

    private void cook(){
        final TextView timer = (TextView) findViewById(R.id.timer);
        final TextView previousStep = (TextView) findViewById(R.id.previous_step);
        final TextView currentStep = (TextView) findViewById(R.id.current_step);
        final TextView nextStep = (TextView) findViewById(R.id.next_step);
        btnNextStep = (Button) findViewById(R.id.btnNextStep);

        finish = false;

        System.out.println("______________________________________");
        System.out.println("TIMER FINISHED");
        System.out.println("i: " + i);
        System.out.println("meal: " + allSteps.get(i).getDescription());
        System.out.println("time: " + i);
        System.out.println("______________________________________");

        //if first step
        if (i == 0) {
            //set previous step to nothing
            previousStep.setText("None");
            //set current step to first step
            currentStep.setText(allSteps.get(i).getMeal()+ ": \n" + allSteps.get(i).getDescription());
            //set next step to next step
            nextStep.setText(allSteps.get(i + 1).getMeal()+ ": \n" + allSteps.get(i + 1).getDescription());

            //if last step
        } else if (i == (allSteps.size() - 1)) {
            enjoyTheMeal();
            return;
        } else {
            previousStep.setText(allSteps.get(i - 1).getMeal()+ ": \n" + allSteps.get(i - 1).getDescription());
            currentStep.setText(allSteps.get(i).getMeal()+ ": \n" + allSteps.get(i).getDescription());
            nextStep.setText(allSteps.get(i + 1).getMeal()+ ": \n" + allSteps.get(i + 1).getDescription());
        }
        //set button to say Cancel Timer
        btnNextStep.setText("End Timer");
        counter = allSteps.get(i).getTime();

        if (counter == 0) {
            timer.setText("Press \"Next Step\" When Complete");
            btnNextStep.setText("Next Step");
            finish = true;
        } else {
            startTimer();
        }

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i++;
                if ((!timer.getText().equals("Finished") || !timer.getText().equals("Press \"Next Step\" When Complete")) && timer1 != null){
                    timer1.cancel();
                    timer.setText("Timer Ended");
                }
                if(i != allSteps.size()) {
                    btnNextStep.setText("Next Step");
                    startCooking();
                } else {
                    timer.setText("");
                    btnNextStep.setText("Finish");
                    finishCooking();
                }

            }
        });

    }

    private void enjoyTheMeal(){
        //set previous step to nothing
        previousStep.setText(allSteps.get(i - 1).getMeal()+ ": \n" + allSteps.get(i - 1).getDescription());
        //set current step to first step
        currentStep.setText(allSteps.get(i).getMeal()+ ": \n" + allSteps.get(i).getDescription());
        nextStepTitle.setText("Last:");
        timer.setText(" ");

        //set next step to next step
        nextStep.setText("Enjoy the Meal");
        btnNextStep.setText("Finish");

        finishCooking();

    }

    private void startTimer(){
        timer1 = new CountDownTimer(counter * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = counter % 60;
                int minutes = counter / 60;
                String stringTime = String.format("%02d:%02d", minutes, seconds);
                timer.setText(stringTime);
                counter--;
            }

            public void onFinish() {
                timer.setText("Finished");
                btnNextStep.setText("Next Step");
                finish = true;
            }
        };
        timer1.start();
    }

    private void finishCooking(){
        btnNextStep = (Button) findViewById(R.id.btnNextStep);


        btnNextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(Activity.RESULT_OK, intent);
                startActivityForResult(intent, REQUEST_CODE_RECIPE_LIST);
            }
        });
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

        meal = "French Orange Poached Pears";
        description = "Warm up the saucepan on the stove to medium heat";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);


        meal = "French Orange Poached Pears";
        description = "Add 1 1/2 cup of orange juice, 1/2 cup brown sugar, 1/4 cup of sugar," +
                " 1 tablespoon of vanilla extract, and 2 teaspoons of ground cinamon to the saucepan";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Bring the mixture to a boil, stirring to dissolve the sugar.";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Place pears in the saucepan, then cover the saucepan.";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait until the timer is complete, then spoon the sauce over the pears";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait unitl the timer is complete, then spoon the sauce over the pears.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait unitl the timer is complete, then spoon the sauce over the pears.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait until the timer is complete, then turn over the pears and spoon the sauce over them.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait until the timer is complete, then spoon the sauce over the pears";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait unitl the timer is complete, then spoon the sauce over the pears.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait unitl the timer is complete, then spoon the sauce over the pears.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait until the timer is complete, then turn over the pears and spoon the sauce over them.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta";
        description = "Grab another pot and bring the water to a boil for the pasta";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta";
        description = "Add the pasta and cook until the timer is done";
        time = 480;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta";
        description = "Drain the pasta and keep in the strainer for now";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Wait unitl the timer is complete, then spoon the sauce over the pears.";
        time = 600;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Black Bean Hummus";
        description = "Mince 1 clove of garlic in the bowl of a food processor";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Black Bean Hummus";
        description = "add 1 can of black beans and half of their reserved liquid in the food processor";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Black Bean Hummus";
        description = "add of 2 tablespoons of lemon juice, 1 1/2 tablespoon of tahini, 3/4 teaspoon" +
                " of ground cumin, 1/2 teaspoon of salt, and 1/4 teaspoon of cayenne pepper";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Black Bean Hummus";
        description = "use the food processor to process until smooth, scraping down the sides as needed.";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);


        meal = "Black Bean Hummus";
        description = "Transfer the Black Bean Hummus to a bowl and garnish with paprika and greek olives";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);


        meal = "French Orange Poached Pears";
        description = "Transfer the pears to individual serving dishes, while continuing to cook the syrup";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);


        meal = "Mexican Pasta";
        description = "Warm up olive oil over medium heat in a larger skillet";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta";
        description = "put onions and peppers in the large skillet";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);


        meal = "Mexican Pasta & French Orange Poached Pears";
        description = "Stir the syrup from the pears often, stir the onions and peppers occasionally";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta";
        description = "In the large skillet stir in 1/2 cup of sweet corn kernels, 1 can of black beans," +
                " 1 can of peeled and diced tomatoes, 1/4 cup of salsa, 1 1/2 tablespoon of taco seasoning," +
                " 1/4 teaspoon of salt, and 1/4 teaspoon of pepper";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta & French Orange Poached Pears";
        description = "Stir the pears syrup often, stir the pasta occasionally";
        time = 300;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "French Orange Poached Pears";
        description = "Mix walnuts into the pears syrup";
        time = 30;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Mexican Pasta & French Orange Poached Pears";
        description = "Pour the syrup over the pears and toss the sauce with the pasta to serve";
        time = 0;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

    }


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

}