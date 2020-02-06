package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Connection;
import io.github.tstewart.CalorieLookup.Food;
import io.github.tstewart.CalorieLookup.Recipe;
import io.github.tstewart.CalorieLookup.error.APICallLimitReachedException;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.nutrients.Nutrient;
import io.github.tstewart.CalorieLookup.request.FoodRequest;
import io.github.tstewart.CalorieLookup.request.RecipeRequest;
import io.github.tstewart.CalorieLookup.request.Request;
import io.github.tstewart.CalorieLookup.util.JSONReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class EdamamConnection extends Connection {

    String url = "https://api.edamam.com/api/food-database/parser?";
    String apiParamFormat = "app_id=%s&app_key=%s";


    /**
     * API key and api ID for accessing edamam API
     */
    private String apiKey;
    private String apiId;

    public EdamamConnection(String apiId, String apiKey) {
        this.apiId = apiId;
        this.apiKey = apiKey;
    }

    /**
     * 1. Sends a HTML request to the Edamam API with the provided API key, ID, and formatted API request
     * @param request Formatted API request
     * @return Returns the HTML response from Edamam
     * @throws InvalidRequestException If the request was malformed (e.g. contained invalid information)
     * @throws APICallLimitReachedException If the Edamam API has reached the maximum number of hourly queries
     */
    @Override
    public JSONObject request(APIRequest request) throws InvalidRequestException, APICallLimitReachedException {
        if(request == null) throw new InvalidRequestException("Please specify a valid APIRequest");
        if(apiKey == null || apiId == null) throw new InvalidRequestException("A valid API id and key must be provided.");

        Request requestType = request.getRequest();
        String requestUrl;

        if(requestType instanceof FoodRequest) {
            requestUrl = createFormattedFoodString(((FoodRequest) requestType).getFoodRequest());
        }
        else if(requestType instanceof RecipeRequest) {
            requestUrl = createFormattedRecipeString((RecipeRequest) requestType);
        }
        else {
            throw new InvalidRequestException("Request type is not supported.");
        }

        try {
            return JSONReader.readJsonFromUrl(requestUrl);
        } catch (JSONException | IOException e) {
            throw new InvalidRequestException("An error occurred while contacting the API. Please try again later or contact the developer.");
        }
    }

    private String createFormattedFoodString(String food) {
        String foodNameFormatted = encode(food);

        return String.format(url + apiParamFormat + "&ingr=%s", apiId, apiKey, foodNameFormatted);
    }

    private String createFormattedRecipeString(String query, ArrayList<Nutrient> nutrientsEaten) {
        throw new UnsupportedOperationException();
    }

    private String encode(String string) {
        return string.replaceAll(" ", "%20");
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
}
