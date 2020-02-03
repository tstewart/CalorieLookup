package io.github.tstewart.CalorieLookup.util;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public interface IJSONParser {

  Food parseFoodResponse(JSONObject object) throws JSONException;
  ArrayList<Recipe> parseRecipeResponse(JSONObject object);

}
