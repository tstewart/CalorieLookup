package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;

public class Food {
    private String foodName;
    private ArrayList<Nutrient> nutritionalInfo;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public ArrayList<Nutrient> getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(ArrayList<Nutrient> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
}
