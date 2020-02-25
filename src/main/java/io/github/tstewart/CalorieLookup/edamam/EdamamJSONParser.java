package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.nutrients.*;
import io.github.tstewart.CalorieLookup.util.IJSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EdamamJSONParser implements IJSONParser {

    private static final String DEFAULT_FOOD_NAME = "No name provided.";
    private static final String DEFAULT_BRAND_NAME = "Generic";
    private static final int MAX_RETURNED_RESULTS = 10;

    @Override
    public ArrayList<Food> parseFoodResponse(final JSONObject object) throws JSONException {
        final JSONArray responses = this.getAllFoodResponsesFromJSON(object);
        final ArrayList<Food> foods = new ArrayList<>();

        if (0 < responses.length()) {
            for (int i = 0; i < responses.length(); i++) {
                final Food food = this.getFoodFromJSON(responses.getJSONObject(i));
                if (null != food) foods.add(food);
            }
        }
        return foods;
    }

    @Override
    public ArrayList<Recipe> parseRecipeResponse(final JSONObject object) throws JSONException {
        final ArrayList<Recipe> recipes = new ArrayList<>(EdamamJSONParser.MAX_RETURNED_RESULTS);
        final JSONArray hits = object.getJSONArray("hits");

        if (null != hits && 0 != hits.length()) {
            for (int i = 0; i < hits.length(); i++) {
                final JSONObject recipe = hits.getJSONObject(i).getJSONObject("recipe");

                String name = null, url = null, icon = null;

                try {
                    name = recipe.getString("label");
                    url = recipe.getString("url");
                    icon = recipe.getString("image");
                } catch (final JSONException e) {
                    if (null == name) name = "Unknown Name";
                    if (null == icon) icon = "No Icon";
                    if (null == url) url = "No Url";
                }

                recipes.add(new Recipe(name, icon, url));
            }
        }

        return recipes;
    }

    private JSONArray getAllFoodResponsesFromJSON(final JSONObject object) throws JSONException {
        final JSONArray responses = new JSONArray();

        final JSONArray hints = object.getJSONArray("hints");

        for (int i = 0; i < hints.length(); i++) {
            try {
                final JSONObject food = hints.getJSONObject(i).getJSONObject("food");
                responses.put(food);
            } catch (final JSONException ignored) {
            }
        }

        try {
            final JSONObject food = object.getJSONArray("parsed").getJSONObject(0);
            responses.put(food);
        } catch (final JSONException ignored) {
        }
        return responses;
    }

    private Food getFoodFromJSON(final JSONObject foodObject) {
        String name;
        String brand;
        final ArrayList<Nutrient> nutrients = new ArrayList<>();
        double calories;

        final JSONObject nutrientsObject;

        try {
            nutrientsObject = foodObject.getJSONObject("nutrients");
        } catch (final JSONException e) {
            return null;
        }

        try {
            name = foodObject.getString("label");
        } catch (final JSONException e) {
            name = EdamamJSONParser.DEFAULT_FOOD_NAME;
        }

        try {
            brand = foodObject.getString("brand");
        } catch (final JSONException e) {
            brand = EdamamJSONParser.DEFAULT_BRAND_NAME;
        }

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

        try {
            calories = nutrientsObject.getDouble(NutrientCodes.Energy.code);
        } catch (final JSONException e) {
            calories = 0;
        }

        return new Food(name, brand, nutrients, calories);
    }

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
