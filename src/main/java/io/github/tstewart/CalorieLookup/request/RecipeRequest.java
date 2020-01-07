package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.NutritionCalculator.UserInfo;

import java.util.ArrayList;

public class RecipeRequest extends Request {
    /**
     * Request string to narrow down recipe request (e.g. a food query of "chicken" will only return chicken based recipes)
     */
    private String foodQuery;
    /**
     * User information
     */
    private UserInfo user;
    /**
     * Nutrients eaten by user already
     */
    private ArrayList<Nutrient> nutrientsEaten;
    /**
     * Nutrients still required
     */
    private ArrayList<Nutrient> targetNutrients;

    public RecipeRequest(String foodQuery, UserInfo user, ArrayList<Nutrient> nutrientsEaten) {
        this.foodQuery = foodQuery;
        this.user = user;
        this.nutrientsEaten = nutrientsEaten;
        this.targetNutrients = getTargetNutrients();
    }

    /**
     * Takes the nutrients already eaten and calculates what they still need to reach nutrient requirements for the day
     * @return The nutrients required to reach daily nutrient requirements
     */
    private ArrayList<Nutrient> getTargetNutrients() {
        return null;
    }

    public String getFoodQuery() {
        return foodQuery;
    }

    public void setFoodQuery(String foodQuery) {
        this.foodQuery = foodQuery;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ArrayList<Nutrient> getNutrientsEaten() {
        return nutrientsEaten;
    }

    public void setNutrientsEaten(ArrayList<Nutrient> nutrientsEaten) {
        this.nutrientsEaten = nutrientsEaten;
    }

    public void setTargetNutrients(ArrayList<Nutrient> targetNutrients) {
        this.targetNutrients = targetNutrients;
    }
}
