package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.nutrients.Carbohydrates;
import io.github.tstewart.CalorieLookup.nutrients.Fiber;
import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.CalorieLookup.request.RecipeRequest;
import io.github.tstewart.NutritionCalculator.UserInfo;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RecipeRequestTest {

    private final String appId = "b617efee";
    private final String apiKey = "174c65055abaac50f2c1250191e46576";

  @Test
  public void recipeRequestNoNutrientsSuccessful() throws InvalidRequestException, InvalidResponseException {
    // Arrange
    final UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
    final HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();

    final RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
    final APIRequest request = new APIRequest();
    request.setRequest(recipeRequest);
      final EdamamConnection connection = new EdamamConnection(this.appId, this.apiKey);

    // Act
    final JSONObject response = connection.request(request);

    // Assert
    assertNotNull(response);
    assertTrue(0 < response.length());
  }

  @Test
  public void recipeRequestNutrientsSuccessful() throws InvalidRequestException, InvalidResponseException {
    // Arrange
    final UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
    final HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
    nutrients.put(Fiber.class, 10.0);
    nutrients.put(Carbohydrates.class, 50.0);

    final RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
    final APIRequest request = new APIRequest();
    request.setRequest(recipeRequest);
    final EdamamConnection connection = new EdamamConnection(this.appId, this.apiKey);

    // Act
    final JSONObject response = connection.request(request);

    // Assert
    assertNotNull(response);
    assertTrue(0 < response.length());
  }

  @Test
  public void recipeRequestInvalidAppKeyThrowsError() throws InvalidResponseException {
    try {
      // Arrange
      final UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
      final HashMap<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
      nutrients.put(Fiber.class, 10.0);
      nutrients.put(Carbohydrates.class, 50.0);

      final RecipeRequest recipeRequest = new RecipeRequest("chicken", info, nutrients, 0);
      final APIRequest request = new APIRequest();
      request.setRequest(recipeRequest);
      final EdamamConnection connection = new EdamamConnection(null, null);

      // Act
      final JSONObject response = connection.request(request);
    } catch (final InvalidRequestException e) {
      assertTrue(e.getMessage().startsWith("A valid API id and key must be provided."));
    }
  }

  @Test
  public void invalidRequestThrowsError() throws InvalidResponseException {
    try {
      // Arrange
      final UserInfo info = new UserInfo(UserInfo.Gender.MALE,21,66,166);
      final Map<Class<? extends Nutrient>, Double> nutrients = new HashMap<>();
      nutrients.put(Fiber.class, 10.0);
      nutrients.put(Carbohydrates.class, 50.0);

      final APIRequest request = new APIRequest();
      request.setRequest(null);
      final EdamamConnection connection = new EdamamConnection(this.appId, this.apiKey);

      // Act
      final JSONObject response = connection.request(request);
    } catch (final InvalidRequestException ignore) { }
  }
}