package io.github.tstewart.CalorieLookup.request;

/**
 * Converts food to API request for food
 */
public class FoodRequest extends Request {

    private String foodRequest;

    public FoodRequest(final String foodRequest) {
        super();
        this.foodRequest = foodRequest;
    }

    public String getFoodRequest() {
        return this.foodRequest;
    }

    public void setFoodRequest(final String foodRequest) {
        this.foodRequest = foodRequest;
    }
}
