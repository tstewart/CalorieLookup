package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.response.IFoodResponseParser;
import io.github.tstewart.CalorieLookup.response.IRecipeResponseParser;

public class EdamamResponseParser {

    public static class FoodResponseParser implements IFoodResponseParser {

        /**
         * Parses the API response string to generate a food
         * @param apiResponse API response string
         * @return Food object with nutritional information
         * @throws InvalidResponseException If the API response string is invalid, or essential information is missing
         */
        @Override
        public Food parseResponse(String apiResponse) throws InvalidResponseException {
            return null;
        }
    }

    public static class RecipeResponseParser implements IRecipeResponseParser {

        /**
         * Parses the API response string to generate a recipe
         * @param apiResponse API response string
         * @return Recipe object with nutritional information
         * @throws InvalidResponseException If the API response string is invalid, or essential information is missing
         */
        @Override
        public Recipe parseResponse(String apiResponse) throws InvalidResponseException {
            return null;
        }
    }
}
