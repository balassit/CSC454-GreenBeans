package com.example.blake.greenbeans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blake on 11/30/16.
 */

public class Ingredient implements Parcelable {
    private double originalQuantity;
    private double quantity;
    private String unit;
    private String name;

    private int mData;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                Double.toString(quantity),
                this.name,
                this.unit
        });
    }

    public Ingredient(double quantity, String unit, String name) {
        this.originalQuantity = quantity;
        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
    }

    public String getUnitString() {
        return unit.equals("") ? "" : unit + (quantity > 1 ? "s" : "") + " ";
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNameString() {
        return name.equals("") ? "" : name + " ";
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getQuantityString() {
        if (quantity < 0) {
            return "";
        }
        else {
            String quantityString = "";
            if ((int) quantity > 0) {
                quantityString += Integer.toString((int) quantity) + " ";
            }

            double fraction = quantity % 1;
            if (Math.abs(fraction - .25) < .01) {
                quantityString += "1/4 ";
            }
            else if (Math.abs(fraction - .5) < .01) {
                quantityString += "1/2 ";
            }
            else if (Math.abs(fraction - .75) < .01) {
                quantityString += " 3/4 ";
            }

            return quantityString + " ";
        }
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public void addOneQuanity(){
        this.quantity += originalQuantity;
    }

    public String getDisplayString() {
        if (this.unit.equals("")) {
            return this.getQuantityString() + this.name + (quantity > 1 ? "s" : "");
        }
        else {
            return this.getQuantityString() + this.getUnitString() + this.getNameString();
        }
    }


    // Parcelling part
    public Ingredient(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.quantity = Double.valueOf(data[0]);
        this.unit = data[1];
        this.name = data[2];
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
