package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.response.IFoodResponseParser;
import io.github.tstewart.CalorieLookup.response.IRecipeResponseParser;

public class EdamamResponseParser {

    public static class FoodResponseParser implements IFoodResponseParser {

        @Override
        public Food parseResponse(String apiResponse) throws InvalidResponseException {
            return null;
        }
    }

    public static class RecipeResponseParser implements IRecipeResponseParser {

        @Override
        public Recipe parseResponse(String apiResponse) throws InvalidResponseException {
            return null;
        }
    }
}
