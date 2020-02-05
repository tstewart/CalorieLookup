package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.Food;

import java.util.ArrayList;

/**
 * Converts food to API request for food
 */
public class FoodRequest extends Request {

    private String foodRequest;

    public FoodRequest(String foodRequest) {
        this.foodRequest = foodRequest;
    }

    public String getFoodRequest() {
        return foodRequest;
    }

    public void setFoodRequest(String foodRequest) {
        this.foodRequest = foodRequest;
    }
}
