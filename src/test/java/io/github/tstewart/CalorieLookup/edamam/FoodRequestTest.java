package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.nutrients.Fiber;
import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.CalorieLookup.request.FoodRequest;
import io.github.tstewart.CalorieLookup.request.RecipeRequest;
import io.github.tstewart.NutritionCalculator.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FoodRequestTest {

  @Test
  public void foodRequestSuccessful() throws InvalidRequestException, InvalidResponseException {
      //Arrange
      EdamamConnection connection = new EdamamConnection("da5ccccd", "830de1530183470f64e9ef0f6352421e");
      FoodRequest request = new FoodRequest("apple");
      APIRequest apiRequest = new APIRequest();
      apiRequest.setRequest(request);

      //Act
      JSONObject response = connection.request(apiRequest);
      String responseString = response.toString();

      System.out.println(response.toString());

      //Assert
      assertNotNull(response);
      assertTrue(response.length() > 0);
  }
}