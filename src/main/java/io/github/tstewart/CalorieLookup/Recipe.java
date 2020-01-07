package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    /**
     * Recipe name
     */
    private String name;
    /**
     * Ingredients list, with the name and quantity of ingredients
     */
    private HashMap<String, Float> ingredients;
    /**
     * Total calories in the meal
     */
    private int calories;
    /**
     * Total nutrients in the meal
     */
    private ArrayList<Nutrient> nurients;

    public Recipe(String name, HashMap<String, Float> ingredients, int calories, ArrayList<Nutrient> nurients) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.nurients = nurients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Float> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, Float> ingredients) {
        this.ingredients = ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public ArrayList<Nutrient> getNurients() {
        return nurients;
    }

    public void setNurients(ArrayList<Nutrient> nurients) {
        this.nurients = nurients;
    }
}
