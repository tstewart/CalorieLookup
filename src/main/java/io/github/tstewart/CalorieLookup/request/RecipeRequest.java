package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.NutritionCalculator.UserInfo;

import java.util.ArrayList;

public class RecipeRequest extends Request {
    String foodQuery;
    UserInfo user;
    ArrayList<Nutrient> nutrientsEaten;
    ArrayList<Nutrient> targetNutrients;

    public RecipeRequest(String foodQuery, UserInfo user, ArrayList<Nutrient> nutrientsEaten) {

    }

    private ArrayList<Nutrient> getTargetNutrients(UserInfo user) {
        return null;
    }
}
