package com.example.blake.greenbeans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blake on 11/30/16.
 */

public class Meal implements Parcelable {
    private String quantity;
    private String recipe;
    private String equipment;
    private String skills;

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
                this.recipe,
                this.equipment,
                this.skills});
    }

    public Meal(String recipe){
        this.quantity = null;
        this.recipe = recipe;
        this.equipment = null;
        this.skills = null;
    }

    public Meal(String quantity, String recipe) {
        this.quantity = quantity;
        this.recipe = recipe;
        this.equipment = null;
        this.skills = null;
    }

    public int getQuantity() {
        if(quantity == null){
            return 0;
        }
        return Integer.parseInt(quantity);
    }

    public String getRecipe() {
        return recipe;
    }

    public String getEquipment(){
        return equipment;
    }

    public String getSkills(){
        return skills;
    }

    public String addToEquipment(char a) {
        if (equipment == null){
            //check that a is null
            equipment = Character.toString(a);
        }
        else {
            if(equipment.indexOf(a) >= 0){
                return equipment;
            }
            equipment = equipment.concat(Character.toString(a));
        }

        return equipment;
    }

    public String addToSkills(char a) {
        if (skills == null){
            //check that a is null
            skills = Character.toString(a);
        }
        else {
            if(skills.indexOf(a) >= 0){
                return skills;
            }
            skills = skills.concat(Character.toString(a));
            System.out.println("_____________________");
            System.out.println("Skills " + skills);
            System.out.println("_____________________");
        }

        return skills;
    }

    /**
     * Change how many of the recipe are in this meal
     * @param quantity
     */
    public void addToQuantity(int quantity) {
        if(quantity > 0){
            int temp = 0;
            if(this.quantity !=null) {
                temp = Integer.parseInt(this.quantity);
            }
            temp += quantity;
            this.quantity = Integer.toString(temp);
        }
    }

    /**
     * Change how many of the recipe are in this meal
     * @param quantity
     */
    public void deleteFromQuantity(int quantity) {
        if (quantity < 0) {
            int temp = 0;
            if (this.quantity != null) {
                temp = Integer.parseInt(this.quantity);
                if (quantity <= temp) {
                    temp -= quantity;
                }
                this.quantity = Integer.toString(temp);
            }
        }
    }

    // Parcelling part
    public Meal(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.quantity = data[0];
        this.recipe = data[1];
        this.equipment = data[2];
        this.skills = data[3];
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
