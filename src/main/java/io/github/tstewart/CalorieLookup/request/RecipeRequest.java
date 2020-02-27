package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.*;
import io.github.tstewart.NutritionCalculator.UserInfo;
import io.github.tstewart.NutritionCalculator.UserNutrition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Thomas Stewart
 */
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
    private List<Nutrient> targetNutrients;

    private int targetCalories;

    public RecipeRequest(final String foodQuery, final UserInfo user, final HashMap<Class<? extends Nutrient>, Double> nutrientsEaten, final int caloriesEaten) {
        super();
        this.foodQuery = foodQuery;
        this.user = user;
        this.nutrientsEaten = nutrientsEaten;
        targetNutrients = this.getTargetNutrients();
        targetCalories = (int) Math.floor(user.getUserNutrition().getCaloriesRequired() - caloriesEaten);
    }

    /**
     * Takes the nutrients already eaten and calculates what they still need to reach nutrient requirements for the day
     *
     * @return The nutrients required to reach daily nutrient requirements
     */
    public ArrayList<Nutrient> getTargetNutrients() {

        // If there are no nutrients eaten, then just return an empty array
        if (null == nutrientsEaten || nutrientsEaten.isEmpty()) {
            return new ArrayList<>();
        }

        final UserNutrition nutrition = this.user.getUserNutrition();
        final ArrayList<Nutrient> targetNutrients = new ArrayList<>();
        this.nutrientsEaten.forEach((nutrientClass, amount) -> {
            try {
                // Try and instantiate a new instance of the class in the nutrients eaten hashmap.
                final Nutrient nutrient = nutrientClass.newInstance();

                // Find what the nutrient class is an instance of, and then create a new instance with the required amount.
                if (nutrient instanceof Carbohydrates) {
                    targetNutrients.add(new Carbohydrates(nutrition.getCarbohydratesRequired() - amount, "CHOCDF"));
                } else if (nutrient instanceof Fat) {
                    targetNutrients.add(new Fat(nutrition.getFatRequired() - amount, "FAT"));
                } else if (nutrient instanceof Fiber) {
                    targetNutrients.add(new Fiber(nutrition.getFiberRequired() - amount, "FIBTG"));
                } else if (nutrient instanceof Protein) {
                    targetNutrients.add(new Protein(nutrition.getProteinRequired() - amount, "PROCNT"));
                }
            } catch (final InstantiationException | IllegalAccessException e) {
                // if the nutrient fails to instantiate, then it should be ignored.
                e.printStackTrace();
            }
        });
        return targetNutrients;
    }

    public String getFoodQuery() {
        return this.foodQuery;
    }

    public void setFoodQuery(final String foodQuery) {
        this.foodQuery = foodQuery;
    }

    public UserInfo getUser() {
        return this.user;
    }

    public void setUser(final UserInfo user) {
        this.user = user;
    }

    public HashMap<Class<? extends Nutrient>, Double> getNutrientsEaten() {
        return this.nutrientsEaten;
    }

    public void setNutrientsEaten(final HashMap<Class<? extends Nutrient>, Double> nutrientsEaten) {
        this.nutrientsEaten = nutrientsEaten;
    }

    public void setTargetNutrients(final ArrayList<Nutrient> targetNutrients) {
        this.targetNutrients = targetNutrients;
    }

    public int getTargetCalories() {
        return this.targetCalories;
    }

    public void setTargetCalories(final int targetCalories) {
        this.targetCalories = targetCalories;
    }
}
