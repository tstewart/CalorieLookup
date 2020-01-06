package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.request.IFoodRequestParser;
import io.github.tstewart.CalorieLookup.request.IRecipeRequestParser;

public class EdamamRequestParser implements IFoodRequestParser, IRecipeRequestParser {

    @Override
    public APIRequest createRequest(Food food) {
        return null;
    }

    @Override
    public APIRequest createRequest(Recipe recipe) {
        return null;
    }

}
