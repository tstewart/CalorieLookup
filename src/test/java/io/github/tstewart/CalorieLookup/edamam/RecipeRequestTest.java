package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.nutrients.Carbohydrates;
import io.github.tstewart.CalorieLookup.nutrients.Fiber;
import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.CalorieLookup.request.FoodRequest;
import io.github.tstewart.CalorieLookup.request.RecipeRequest;
import io.github.tstewart.NutritionCalculator.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

public class RecipeRequestTest {

    String appId = "b617efee";
    String apiKey = "174c65055abaac50f2c1250191e46576";

  @Test
  public void recipeRequestNoNutrientsSuccessful() throws InvalidRequestException, InvalidResponseException {
    // Arrange
    UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
    HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();

    RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
    APIRequest request = new APIRequest();
    request.setRequest(recipeRequest);
      EdamamConnection connection = new EdamamConnection(appId, apiKey);

    // Act
    JSONObject response = connection.request(request);

    // Assert
    assertNotNull(response);
    assertTrue(response.length() > 0);
  }

  @Test
  public void recipeRequestNutrientsSuccessful() throws InvalidRequestException, InvalidResponseException {
    // Arrange
    UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
    HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
    nutrients.put(Fiber.class, 10.0);
    nutrients.put(Carbohydrates.class, 50.0);

    RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
    APIRequest request = new APIRequest();
    request.setRequest(recipeRequest);
    EdamamConnection connection = new EdamamConnection(appId, apiKey);

    // Act
    JSONObject response = connection.request(request);

    // Assert
    assertNotNull(response);
    assertTrue(response.length() > 0);
  }

  @Test
  public void recipeRequestInvalidAppKeyThrowsError() throws InvalidResponseException {
    try {
      // Arrange
      UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
      HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
      nutrients.put(Fiber.class, 10.0);
      nutrients.put(Carbohydrates.class, 50.0);

      RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
      APIRequest request = new APIRequest();
      request.setRequest(recipeRequest);
      EdamamConnection connection = new EdamamConnection(null, null);

      // Act
      JSONObject response = connection.request(request);
    } catch (InvalidRequestException e) {
      assertTrue(e.getMessage().startsWith("A valid API id and key must be provided."));
    }
  }

  @Test
  public void invalidRequestThrowsError() throws InvalidResponseException {
    try {
      // Arrange
      UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
      HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
      nutrients.put(Fiber.class, 10.0);
      nutrients.put(Carbohydrates.class, 50.0);

      APIRequest request = new APIRequest();
      request.setRequest(null);
      EdamamConnection connection = new EdamamConnection(appId, apiKey);

      // Act
      JSONObject response = connection.request(request);
    } catch (InvalidRequestException ignore) { }
  }
}