package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.request.FoodRequest;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FoodRequestTest {

  @Test
  public void foodRequestSuccessful() throws InvalidRequestException, InvalidResponseException {
      //Arrange
      final EdamamConnection connection = new EdamamConnection("da5ccccd", "830de1530183470f64e9ef0f6352421e");
      final FoodRequest request = new FoodRequest("apple");
      final APIRequest apiRequest = new APIRequest();
      apiRequest.setRequest(request);

      //Act
      final JSONObject response = connection.request(apiRequest);

      //Assert
      assertNotNull(response);
      assertTrue(0 < response.length());
  }
}