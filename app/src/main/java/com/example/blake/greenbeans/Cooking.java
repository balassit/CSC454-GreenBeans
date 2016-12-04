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
    private Button btnNextStep;
    private Button btnPauseTimer;


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
        //btnPauseTimer = (Button) findViewById(R.id.btnPauseTimer);

        //set previous step to nothing
        //previousStep.setText();
        //set current step to first step
        currentStep.setText(allSteps.get(0).getDescription());
        //set next step to next step
        nextStep.setText(allSteps.get(1).getDescription());
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
    private boolean finish;
    private boolean noTimer;

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
            currentStep.setText(allSteps.get(i).getDescription());
            //set next step to next step
            nextStep.setText(allSteps.get(i + 1).getDescription());

            //if last step
        } else if (i == (allSteps.size() - 1)) {
            //set previous step to nothing
            previousStep.setText(allSteps.get(i - 1).getDescription());
            //set current step to first step
            currentStep.setText(allSteps.get(i).getDescription());
            //set next step to next step
            nextStep.setText("Last Step");

        } else {
            previousStep.setText(allSteps.get(i - 1).getDescription());
            currentStep.setText(allSteps.get(i).getDescription());
            nextStep.setText(allSteps.get(i + 1).getDescription());
        }
        //set button to say Cancel Timer
        btnNextStep.setText("End Timer");
        counter = allSteps.get(i).getTime();
        noTimer = false;
        if (counter != 0){
            startTimer();
        } else {
            timer.setText("Press \"Next Step\" When Complete");
            btnNextStep.setText("Next Step");
            finish = true;
        }

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i++;
                if (!timer.getText().equals("Finished") || !timer.getText().equals("Press \"Next Step\" When Complete")){
                    timer1.cancel();
                    timer.setText("Timer Canceled");
                }
                if(i != allSteps.size()) {
                    btnNextStep.setText("Next Step");
                    startCooking();
                } else {
                    btnNextStep.setText("Finish");
                    finishCooking();
                }

            }
        });

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

        //NOT SURE IF THIS WILL REFERENCE TEMP AND CHANGE IT EACH TIME
        //BLAKE CARTER LOOK AT THIS
        //ALSO THIS IS HARD CODING HELL hahahahaha

        meal = "Black Bean Hummus";
        description = "first Step Description";
        time = 10;
        temp = new Step(meal, description, time);
        allSteps.add(temp);

        meal = "Black Bean Hummus";
        description = "first Step Description";
        time = 0;
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