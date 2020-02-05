package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;

public class Food {
    /**
     * Food string (e.g. Apple)
     */
    private String foodName;

    /**
     * Brand associated with the product (e.g. McDonald's fries)
     */
    private String brandName;
    /**
     * Nutritional values of the food, received from the API
     */
    private ArrayList<Nutrient> nutritionalInfo;

    private double calories;

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public Food(String foodName, String brandName,
        ArrayList<Nutrient> nutritionalInfo, double calories) {
        this.foodName = foodName;
        this.brandName = brandName;
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

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  @Override
  public String toString() {
    return "Food{" +
        "foodName='" + foodName + '\'' +
        ", brandName='" + brandName + '\'' +
        ", nutritionalInfo=" + nutritionalInfo +
        ", calories=" + calories +
        '}';
  }
}
