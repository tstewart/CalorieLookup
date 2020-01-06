package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.NutritionCalculator.UserInfo;

import java.util.ArrayList;

public class RecipeRequest extends Request {
    private String foodQuery;
    private UserInfo user;
    private ArrayList<Nutrient> nutrientsEaten;
    private ArrayList<Nutrient> targetNutrients;

    public RecipeRequest(String foodQuery, UserInfo user, ArrayList<Nutrient> nutrientsEaten) {

    }

    private ArrayList<Nutrient> getTargetNutrients(UserInfo user) {
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

    public ArrayList<Nutrient> getTargetNutrients() {
        return targetNutrients;
    }

    public void setTargetNutrients(ArrayList<Nutrient> targetNutrients) {
        this.targetNutrients = targetNutrients;
    }
}
