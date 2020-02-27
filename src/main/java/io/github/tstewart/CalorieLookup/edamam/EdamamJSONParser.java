package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.nutrients.*;
import io.github.tstewart.CalorieLookup.util.IJSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Author: Thomas Stewart
 */
public class EdamamJSONParser implements IJSONParser {

    // Food name if none is found.
    private static final String DEFAULT_FOOD_NAME = "No name provided.";
    // Brand name if none is found.
    private static final String DEFAULT_BRAND_NAME = "Generic";
    // Maximum amount of results that can be shown.
    private static final int MAX_RETURNED_RESULTS = 10;

    /**
     * Parse the JSON object returned from Edamam to a list of food objects.
     * @param object JSON object to be parsed.
     * @return An ArrayList of food objects parsed from the function.
     * @throws JSONException If the parsing procedure can not be completed.
     */
    @Override
    public ArrayList<Food> parseFoodResponse(final JSONObject object) throws JSONException {
        // Get all json objects that match the "food" parent.
        final JSONArray responses = this.getAllFoodResponsesFromJSON(object);
        final ArrayList<Food> foods = new ArrayList<>();

        // If there were food objects to be parsed
        if (0 < responses.length()) {
            // For each food JSON object
            for (int i = 0; i < responses.length(); i++) {
                // Parse the food JSON object into a real food object.
                final Food food = this.getFoodFromJSON(responses.getJSONObject(i));
                // If the food object is not null, add this food to the list of parsed foods.
                if (null != food) foods.add(food);
            }
        }
        return foods;
    }

    /**
     * Parse the JSON object returned from Edamam to a list of recipe objects.
     * @param object JSON object to be parsed.
     * @return An ArrayList of recipe objects parsed from the function.
     * @throws JSONException If the parsing procedure can not be completed.
     */
    @Override
    public ArrayList<Recipe> parseRecipeResponse(final JSONObject object) throws JSONException {
        // Establish an ArrayList with a maximum number of results
        final ArrayList<Recipe> recipes = new ArrayList<>(EdamamJSONParser.MAX_RETURNED_RESULTS);
        // Get the array of "hits" from the JSON response
        final JSONArray hits = object.getJSONArray("hits");

        // If results are found
        if (null != hits && 0 != hits.length()) {
            // For each result
            for (int i = 0; i < hits.length(); i++) {
                // Get the recipe object at the array position
                final JSONObject recipe = hits.getJSONObject(i).getJSONObject("recipe");

                String name = null, url = null, icon = null;

                // Try and build a recipe object by searching the JSON object for variables.
                try {
                    name = recipe.getString("label");
                    url = recipe.getString("url");
                    icon = recipe.getString("image");
                } catch (final JSONException e) {
                    // If a value cannot be found, set the value to a placeholder.
                    if (null == name) name = "Unknown Name";
                    if (null == icon) icon = "No Icon";
                    if (null == url) url = "No Url";
                }

                recipes.add(new Recipe(name, icon, url));
            }
        }

        return recipes;
    }

    /**
     * Get all food responses from the Edamam JSON response
     * @param object JSON object to be parsed.
     * @return A JSON Array containing all the food objects, to be parsed later.
     * @throws JSONException If the parsing procedure could not be completed.
     */
    private JSONArray getAllFoodResponsesFromJSON(final JSONObject object) throws JSONException {
        final JSONArray responses = new JSONArray();

        final JSONArray hints = object.getJSONArray("hints");

        for (int i = 0; i < hints.length(); i++) {
            try {
                // Get all food objects in the "hints" array
                final JSONObject food = hints.getJSONObject(i).getJSONObject("food");
                responses.put(food);
            } catch (final JSONException ignored) {
            }
        }

        try {
            // Get the generic parsed food, and add this to the responses
            final JSONObject food = object.getJSONArray("parsed").getJSONObject(0);
            responses.put(food);
        } catch (final JSONException ignored) {
        }
        return responses;
    }

    /**
     * Parse a food object from JSON
     * @param foodObject Object containing a food object to be parsed.
     * @return A food object with the values obtained from JSON.
     */
    private Food getFoodFromJSON(final JSONObject foodObject) {
        String name;
        String brand;
        final ArrayList<Nutrient> nutrients = new ArrayList<>();
        double calories;

        final JSONObject nutrientsObject;

        // Try and get the nutrients object.
        try {
            nutrientsObject = foodObject.getJSONObject("nutrients");
        } catch (final JSONException e) {
            // If nutrient info cant be found, don't return a food object.
            return null;
        }

        // Try and get the name of the food.
        try {
            name = foodObject.getString("label");
        } catch (final JSONException e) {
            // If no name was found, set the name to the default.
            name = EdamamJSONParser.DEFAULT_FOOD_NAME;
        }

        // Try and get the brand of the food.
        try {
            brand = foodObject.getString("brand");
        } catch (final JSONException e) {
            // If no brand was found, set the brand name to default.
            brand = EdamamJSONParser.DEFAULT_BRAND_NAME;
        }

        /**
         * Try and get the nutrient information for each nutrient that could be in the food.
         * If no nutrient information can be found, then assume the food doesn't contain said nutrient.
         */
        try {
            nutrients.add(new Carbohydrates(nutrientsObject.getDouble(NutrientCodes.Carbohydrates.code), NutrientCodes.Carbohydrates.code));
        } catch (final JSONException e) {
            nutrients.add(new Carbohydrates(0.0, NutrientCodes.Carbohydrates.code));
        }

        try {
            nutrients.add(new Fat(nutrientsObject.getDouble(NutrientCodes.Fat.code), NutrientCodes.Fat.code));
        } catch (final JSONException e) {
            nutrients.add(new Fat(0.0, NutrientCodes.Fat.code));
        }

        try {
            nutrients.add(new Fiber(nutrientsObject.getDouble(NutrientCodes.Fiber.code), NutrientCodes.Fiber.code));
        } catch (final JSONException e) {
            nutrients.add(new Fiber(0.0, NutrientCodes.Fiber.code));
        }

        try {
            nutrients.add(new Protein(nutrientsObject.getDouble(NutrientCodes.Protein.code), NutrientCodes.Protein.code));
        } catch (final JSONException e) {
            nutrients.add(new Protein(0.0, NutrientCodes.Protein.code));
        }

        // Calories are represented by the Energy nutrient code.
        try {
            calories = nutrientsObject.getDouble(NutrientCodes.Energy.code);
        } catch (final JSONException e) {
            calories = 0;
        }

        return new Food(name, brand, nutrients, calories);
    }

    // An enumerator of the nutrient codes that are expected to be present in Edamam's JSON responses.
    protected enum NutrientCodes {
        Carbohydrates("CHOCDF"),
        Fat("FAT"),
        Energy("ENERC_KCAL"),
        Fiber("FIBTG"),
        Protein("PROCNT");

        final String code;

        NutrientCodes(final String code) {
            this.code = code;
        }
    }

}
