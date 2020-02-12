package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.*;
import io.github.tstewart.NutritionCalculator.UserInfo;
import io.github.tstewart.NutritionCalculator.UserNutrition;

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

    private double targetCalories;

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
    public ArrayList<Nutrient> getTargetNutrients() {

        if(nutrientsEaten == null || nutrientsEaten.size() == 0) {
            return new ArrayList<>();
        }

        UserNutrition nutrition = user.getUserNutrition();
        ArrayList<Nutrient> targetNutrients = new ArrayList<>();
        nutrientsEaten.forEach((nutrient -> {
            if(nutrient instanceof Carbohydrates) {
                targetNutrients.add(new Carbohydrates(nutrient.getAmount() - nutrition.getCaloriesRequired(), nutrient.getNtrCode()));
            }
            else if(nutrient instanceof Fat) {
                targetNutrients.add(new Fat(nutrient.getAmount() - nutrition.getFatRequired(), nutrient.getNtrCode()));
            }
            else if(nutrient instanceof Fiber) {
                targetNutrients.add(new Fiber(nutrient.getAmount() - nutrition.getFiberRequired(), nutrient.getNtrCode()));
            }
            else if(nutrient instanceof Protein) {
                targetNutrients.add(new Protein(nutrient.getAmount() - nutrition.getProteinRequired(), nutrient.getNtrCode()));
            }
        }));
        return targetNutrients;
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
