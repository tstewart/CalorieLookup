package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;

/**
 * Author: Thomas Stewart
 */
public class Food {
    /**
     * Food string (e.g. Apple)
     */
    private String foodName;

    /**
     * Brand associated with the product (e.g. McDonald's fries)
     */
    private String brandName = null;
    /**
     * Nutritional values of the food, received from the API
     */
    private ArrayList<Nutrient> nutritionalInfo = null;

    private double calories = 0.0;

    public Food(final String foodName) {
        super();
        this.foodName = foodName;
    }

    public Food(final String foodName, final String brandName,
                final ArrayList<Nutrient> nutritionalInfo, final double calories) {
        super();
        this.foodName = foodName;
        this.brandName = brandName;
        this.nutritionalInfo = nutritionalInfo;
        this.calories = calories;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public void setFoodName(final String foodName) {
        this.foodName = foodName;
    }

    public Iterable<Nutrient> getNutritionalInfo() {
        return this.nutritionalInfo;
    }

    public void setNutritionalInfo(final ArrayList<Nutrient> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(final double calories) {
        this.calories = calories;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        final StringBuilder nutrients = new StringBuilder();
        this.getNutritionalInfo().forEach((nutrient -> nutrients.append(nutrient).append(" ")));

        return "Name: " + this.getFoodName() + '\n' +
                "Brand: " + this.getBrandName() + '\n' +
                "Calories: " + this.getCalories() + '\n' +
                "Nutrients: " + nutrients;
    }
}
