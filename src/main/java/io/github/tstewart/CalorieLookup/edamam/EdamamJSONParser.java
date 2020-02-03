package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.util.IJSONParser;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class EdamamJSONParser implements IJSONParser {

  @Override
  public Food parseFoodResponse(JSONObject object) throws JSONException {
    Object parsed = object.get("parsed");
    return null;
  }

  @Override
  public ArrayList<Recipe> parseRecipeResponse(JSONObject object) {
    return null;
  }
}
