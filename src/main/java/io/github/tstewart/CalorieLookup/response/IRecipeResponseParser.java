package io.github.tstewart.CalorieLookup.response;

import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;

public interface IRecipeResponseParser {
    Recipe parseResponse(String apiResponse) throws InvalidResponseException;
}
