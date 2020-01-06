package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String name;
    private HashMap<String, Float> ingredients;
    private int calories;
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
