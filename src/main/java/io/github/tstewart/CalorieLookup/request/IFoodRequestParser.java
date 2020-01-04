package io.github.tstewart.CalorieLookup.request;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Food;

public interface IFoodRequestParser {
    APIRequest createRequest(Food food);
}
