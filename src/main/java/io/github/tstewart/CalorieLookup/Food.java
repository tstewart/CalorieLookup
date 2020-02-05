package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;

public class Food {
    /**
     * Food string (e.g. Apple)
     */
    private String foodName;
    /**
     * Nutritional values of the food, received from the API
     */
    private ArrayList<Nutrient> nutritionalInfo;

    private double calories;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public Food(String foodName,
        ArrayList<Nutrient> nutritionalInfo, double calories) {
        this.foodName = foodName;
        this.nutritionalInfo = nutritionalInfo;
        this.calories = calories;
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

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
