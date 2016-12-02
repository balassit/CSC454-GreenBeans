package com.example.blake.greenbeans;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> recipes;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private static final int REQUEST_CODE_CHECK_OUT = 200;
    private static final int REQUEST_CODE_VIEW_RECIPE = 300;
    private ArrayList<Meal> mealList = new ArrayList<Meal>();
    private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        //set up the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mealList = getIntent().getParcelableArrayListExtra("mealList");
        ingredientList = getIntent().getParcelableArrayListExtra("ingredientList");
        //set the names of the recipes
        setRecipes();
        //display list of recipes
        displayList();

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
                Intent intent = new Intent(getApplicationContext(), MealCheckout.class);
                System.out.println("");
                intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
                intent.putParcelableArrayListExtra("ingredientList", (ArrayList<? extends Parcelable>) ingredientList);
                startActivityForResult(intent, REQUEST_CODE_CHECK_OUT);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    class ItemList implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ViewGroup viewGroup = (ViewGroup)view;
            TextView textView = (TextView) viewGroup.findViewById(R.id.txtItem);
            //Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            //go to recipe with name of recipe
            Intent intent = new Intent(getApplicationContext(), RecipeView.class);
            intent.putExtra("name", textView.getText().toString());
            intent.putParcelableArrayListExtra("mealList", (ArrayList<? extends Parcelable>) mealList);
            intent.putParcelableArrayListExtra("ingredientList", (ArrayList<? extends Parcelable>) ingredientList);
            startActivityForResult(intent, REQUEST_CODE_VIEW_RECIPE);
        }
    }

    /**
     * set up itesm from list.
     * if item is clicked the setOnItemClickListener will take content to RecipeView
     */
    private void displayList() {
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem, recipes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemList());
    }

    /**
     * Set the names of the recipes a user can select.
     */
    private void setRecipes(){
        listView = (ListView) findViewById(R.id.listRecipes);
        String[] items = {"Apple", "banana", "grape"};
        recipes = new ArrayList<>(Arrays.asList(items));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_CHECK_OUT:
                if (resultCode == Activity.RESULT_OK) {
                    data.getStringExtra("result");
                    //finish?
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;
        }
    }
}