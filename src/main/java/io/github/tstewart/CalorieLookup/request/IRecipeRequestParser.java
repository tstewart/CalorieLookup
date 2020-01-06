package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Recipe;

public interface IRecipeRequestParser {
    APIRequest createRequest(Recipe recipe);
}
