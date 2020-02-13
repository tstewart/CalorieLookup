package io.github.tstewart.CalorieLookup.util;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public interface IJSONParser {

  ArrayList<Food> parseFoodResponse(JSONObject object) throws JSONException, InvalidRequestException;
  ArrayList<Recipe> parseRecipeResponse(JSONObject object) throws JSONException;

}
