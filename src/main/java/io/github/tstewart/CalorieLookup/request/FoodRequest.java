package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.Food;

import java.util.ArrayList;

/**
 * Converts food to API request for food
 */
public class FoodRequest extends Request {

    private Food food;

    public FoodRequest(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
