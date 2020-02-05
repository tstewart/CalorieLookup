package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.nutrients.Carbohydrates;
import io.github.tstewart.CalorieLookup.nutrients.Fat;
import io.github.tstewart.CalorieLookup.nutrients.Fiber;
import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.CalorieLookup.nutrients.Protein;
import io.github.tstewart.CalorieLookup.util.IJSONParser;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EdamamJSONParser implements IJSONParser {

  final static String DEFAULT_FOOD_NAME = "No name provided.";
  final static String DEFAULT_BRAND_NAME = "Generic";

  @Override
  public ArrayList<Food> parseFoodResponse(JSONObject object) throws JSONException {
    JSONArray responses = getAllFoodResponsesFromJSON(object);
    ArrayList<Food> foods = new ArrayList<>();

    if(responses.length() > 0) {
      for(int i  = 0; i < responses.length(); i++) {
        Food food = getFoodFromJSON(responses.getJSONObject(i));
        if(food != null) foods.add(food);
      }
    }
    else {
      return null;
    }

    return foods;
  }

  @Override
  public ArrayList<Recipe> parseRecipeResponse(JSONObject object) {
    return null;
  }

  private JSONArray getAllFoodResponsesFromJSON(JSONObject object) throws JSONException {
    JSONArray responses = new JSONArray();

    JSONArray hints = object.getJSONArray("hints");

    for(int i = 0; i < hints.length(); i++) {
      try {
        JSONObject food = hints.getJSONObject(i).getJSONObject("food");
        responses.put(food);
      }
      catch(JSONException ignored) { }
    }

    try {
      JSONObject food = object.getJSONArray("parsed").getJSONObject(0);
      responses.put(food);
    }
    catch(JSONException ignored){}
    return responses;
  }

  private Food getFoodFromJSON(JSONObject foodObject) {
    String name;
    String brand;
    ArrayList<Nutrient> nutrients = new ArrayList<>();
    double calories;

    JSONObject nutrientsObject;

    try {
      nutrientsObject = foodObject.getJSONObject("nutrients");
    }
    catch (JSONException e) {
      return null;
    }

    try {
      name = foodObject.getString("label");
    }
    catch(JSONException e) {
      name = DEFAULT_FOOD_NAME;
    }

    try {
      brand = foodObject.getString("brand");
    } catch (JSONException e) {
      brand = DEFAULT_BRAND_NAME;
    }

    try {
      nutrients.add(new Carbohydrates(nutrientsObject.getDouble(NutrientCodes.Carbohydrates.code), NutrientCodes.Carbohydrates.code));
    } catch (JSONException e) {
      nutrients.add(new Carbohydrates(0.0, NutrientCodes.Carbohydrates.code));
    }

    try {
      nutrients.add(new Fat(nutrientsObject.getDouble(NutrientCodes.Fat.code), NutrientCodes.Fat.code));
    } catch (JSONException e) {
      nutrients.add(new Fat(0.0, NutrientCodes.Fat.code));
    }

    try {
      nutrients.add(new Fiber(nutrientsObject.getDouble(NutrientCodes.Fiber.code), NutrientCodes.Fiber.code));
    } catch (JSONException e) {
      nutrients.add(new Fiber(0.0, NutrientCodes.Fiber.code));
    }

    try {
      nutrients.add(new Protein(nutrientsObject.getDouble(NutrientCodes.Protein.code), NutrientCodes.Protein.code));
    } catch (JSONException e) {
      nutrients.add(new Protein(0.0, NutrientCodes.Protein.code));
    }

    try {
      calories = nutrientsObject.getDouble(NutrientCodes.Energy.code);
    } catch (JSONException e) {
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

    String code;

    NutrientCodes(String code) {
      this.code = code;
    }
  }

}
