package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.request.IFoodRequestParser;
import io.github.tstewart.CalorieLookup.request.IRecipeRequestParser;

public class EdamamRequestParser implements IFoodRequestParser, IRecipeRequestParser {

    /**
     * Creates a formatted API request from the provided food
     * @param food Food to be formatted
     * @return Formatted API request
     */
    @Override
    public APIRequest createRequest(Food food) {
        return null;
    }

    /**
     * Creates a formatted API request from the provided recipe
     * @param recipe Recipe to be formatted
     * @return Formatted API request
     */
    @Override
    public APIRequest createRequest(Recipe recipe) {
        return null;
    }

}
