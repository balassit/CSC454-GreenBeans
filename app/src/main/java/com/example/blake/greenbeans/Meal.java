package com.example.blake.greenbeans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blake on 11/30/16.
 */

public class Meal implements Parcelable {
    private String quantity;
    private String recipe;

    private int mData;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.quantity,
                this.recipe});
    }

    public Meal(){
        this.quantity = null;
        this.recipe = null;
    }

    public Meal(String quantity, String recipe) {
        this.quantity = quantity;
        this.recipe = recipe;
    }

    public int getQuantity() {
        return Integer.parseInt(quantity);
    }

    public String getRecipe() {
        return recipe;
    }

    /**
     * Change how many of the recipe are in this meal
     * @param quantity
     */
    public void setQuantity(int quantity) {
        if(quantity > 0){
            this.quantity = Integer.toString(quantity);
        }
        else {
            this.quantity = Integer.toString(0);
        }
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    // Parcelling part
    public Meal(Parcel in){
        String[] data = new String[2];

        in.readStringArray(data);
        this.quantity = data[0];
        this.recipe = data[1];
    }



    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
