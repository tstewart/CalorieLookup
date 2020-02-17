package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.*;
import io.github.tstewart.NutritionCalculator.UserInfo;
import io.github.tstewart.NutritionCalculator.UserNutrition;

import java.util.ArrayList;
import java.util.HashMap;

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
     * Nutrient class, amount in double
     */
    private HashMap<Class<? extends Nutrient>, Double> nutrientsEaten;
    /**
     * Nutrients still required
     */
    private ArrayList<Nutrient> targetNutrients;

    private int targetCalories;

    public RecipeRequest(String foodQuery, UserInfo user, HashMap<Class<? extends Nutrient>, Double> nutrientsEaten, int caloriesEaten) {
        this.foodQuery = foodQuery;
        this.user = user;
        this.nutrientsEaten = nutrientsEaten;
        this.targetNutrients = getTargetNutrients();
        this.targetCalories = (int) Math.floor(user.getUserNutrition().getCaloriesRequired() - caloriesEaten);
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
        nutrientsEaten.forEach((nutrientClass, amount) -> {
            try {
                Nutrient nutrient = nutrientClass.newInstance();

                if(nutrient instanceof Carbohydrates) {
                    targetNutrients.add(new Carbohydrates(amount - nutrition.getCarbohydratesRequired(), nutrient.getNtrCode()));
                }
                else if(nutrient instanceof Fat) {
                    targetNutrients.add(new Fat(amount - nutrition.getFatRequired(), nutrient.getNtrCode()));
                }
                else if(nutrient instanceof Fiber) {
                    targetNutrients.add(new Fiber(amount- nutrition.getFiberRequired(), nutrient.getNtrCode()));
                }
                else if(nutrient instanceof Protein) {
                    targetNutrients.add(new Protein(amount - nutrition.getProteinRequired(), nutrient.getNtrCode()));
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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

    public HashMap<Class<? extends Nutrient>, Double> getNutrientsEaten() {
        return nutrientsEaten;
    }

    public void setNutrientsEaten(HashMap<Class<? extends Nutrient>, Double> nutrientsEaten) {
        this.nutrientsEaten = nutrientsEaten;
    }

    public void setTargetNutrients(ArrayList<Nutrient> targetNutrients) {
        this.targetNutrients = targetNutrients;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }
}
