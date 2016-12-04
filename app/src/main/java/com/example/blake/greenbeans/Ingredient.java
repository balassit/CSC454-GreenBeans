package com.example.blake.greenbeans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blake on 11/30/16.
 */

public class Ingredient implements Parcelable {
    private String quantity;
    private String unit;
    private String name;
    private String original;

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
                this.quantity,
                this.name,
                this.unit,
                this.original});
    }


    public Ingredient(double quantity, String unit, String name) {
        this.quantity = "0";
        if(quantity > 0) {
            this.quantity = Double.toString(quantity);
        }
        this.name = unit;
        this.unit = name;
        this.original = Double.toString(quantity);;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        if(quantity == null){
            return 0;
        }
        return Double.parseDouble(quantity);
    }

    public String getQuantityString() {
        if(quantity == null){
            return "0";
        }
        return quantity;
    }

    public void setQuantity(double quantity) {
        if(quantity > 0) {
            this.quantity = Double.toString(quantity);
        }
    }

    public String getOriginal(){
        return this.original;
    }


    public void addOneQuanity(){
        double set = Double.parseDouble(this.quantity) + Double.parseDouble(this.original);
        this.quantity = Double.toString(set);
    }


    // Parcelling part
    public Ingredient(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.quantity = data[0];
        this.unit = data[1];
        this.name = data[2];
        this.original = data[3];
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
