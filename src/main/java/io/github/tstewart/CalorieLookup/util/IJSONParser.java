package io.github.tstewart.CalorieLookup.util;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public interface IJSONParser {

    ArrayList<Food> parseFoodResponse(JSONObject object) throws JSONException;

    ArrayList<Recipe> parseRecipeResponse(JSONObject object) throws JSONException;

}
