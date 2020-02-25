package io.github.tstewart.CalorieLookup.response;

import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;

public interface IFoodResponseParser {
    Food parseResponse(String apiResponse);
}
