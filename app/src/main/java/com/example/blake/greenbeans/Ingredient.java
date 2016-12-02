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
                this.unit});
    }


    public Ingredient(String quantity, String name, String unit) {
        this.quantity = quantity;
        this.name = unit;
        this.unit = name;
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
        return Integer.parseInt(quantity);
    }

    public void setQuantity(double quantity) {
        if(quantity > 0) {
            this.quantity = Double.toString(quantity);
        }
    }

    // Parcelling part
    public Ingredient(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.quantity = data[0];
        this.name = data[1];
        this.unit = data[2];
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
