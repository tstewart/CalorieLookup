package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.APIRequest;
import io.github.tstewart.CalorieLookup.Connection;
import io.github.tstewart.CalorieLookup.error.InvalidRequestException;
import io.github.tstewart.CalorieLookup.error.InvalidResponseException;
import io.github.tstewart.CalorieLookup.request.FoodRequest;
import io.github.tstewart.CalorieLookup.request.RecipeRequest;
import io.github.tstewart.CalorieLookup.request.Request;
import io.github.tstewart.CalorieLookup.util.JSONReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EdamamConnection extends Connection {

    private final String edamamFoodSearchUrl = "https://api.edamam.com/api/food-database/parser?";
    private final String edamamRecipeSearchUrl = "https://api.edamam.com/search?";
    private final String apiParamFormat = "app_id=%s&app_key=%s";


    /**
     * API key and api ID for accessing edamam API
     */
    private String apiKey;
    private String apiId;

    public EdamamConnection(final String apiId, final String apiKey) {
        super();
        this.apiId = apiId;
        this.apiKey = apiKey;
    }

    /**
     * 1. Sends a HTML request to the Edamam API with the provided API key, ID, and formatted API request
     *
     * @param request Formatted API request
     * @return Returns the HTML response from Edamam
     * @throws InvalidRequestException If the request was malformed (e.g. contained invalid information)
     */
    @Override
    public JSONObject request(final APIRequest request) throws InvalidRequestException, InvalidResponseException {
        if (null == request) throw new InvalidRequestException("Please specify a valid APIRequest");
        if (null == apiKey || null == apiId)
            throw new InvalidRequestException("A valid API id and key must be provided.");

        final Request requestType = request.getRequest();
        final String requestUrl;

        if (requestType instanceof FoodRequest) {
            requestUrl = this.createFormattedFoodString(((FoodRequest) requestType).getFoodRequest());
        } else if (requestType instanceof RecipeRequest) {
            requestUrl = this.createFormattedRecipeString((RecipeRequest) requestType);
        } else {
            throw new InvalidRequestException("Request type is not supported.");
        }

        try {
            return JSONReader.readJsonFromUrl(requestUrl);
        } catch (final JSONException | IOException e) {
            final String errorMsg = e.getMessage();
            if (null != errorMsg) {
                // If the error response is a fault on behalf of the server
                if (errorMsg.startsWith("Server returned HTTP response code")) {
                    final String errorCode = errorMsg.split("for URL")[0];
                    throw new InvalidResponseException(errorCode);
                }
            }
            throw new InvalidRequestException("An error occurred while contacting the API. Please try again later or contact the developer.");
        }
    }

    private String createFormattedFoodString(final String food) {
        final String foodNameFormatted = this.encode(food);

        return String.format(this.edamamFoodSearchUrl + this.apiParamFormat + "&ingr=%s", this.apiId, this.apiKey, foodNameFormatted);
    }

    private String createFormattedRecipeString(final RecipeRequest recipeRequest) {
        final String recipeQuery = this.encode(recipeRequest.getFoodQuery());
        final String mealTypeOverride = "mealtype=dinner";

        final StringBuilder nutrients = new StringBuilder();
        recipeRequest.getTargetNutrients().forEach((nutrient -> nutrients.append("nutrients[").append(nutrient.getNtrCode()).append("]=").append((int) Math.floor(nutrient.getAmount())).append("&")));

        final String nutrientsEncoded = this.encode(nutrients.toString());

        nutrients.append("calories=").append(recipeRequest.getTargetCalories());

        return String.format(this.edamamRecipeSearchUrl + this.apiParamFormat + "&q=%s&%s&%s", this.apiId, this.apiKey, recipeQuery, mealTypeOverride, nutrientsEncoded);
    }

    private String encode(String string) {
        string = string.replace(" ", "%20");
        string = string.replace("[", "%5B");
        string = string.replace("]", "%5D");
        string = string.replace("'", "%27");
        return string;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiId() {
        return this.apiId;
    }

    public void setApiId(final String apiId) {
        this.apiId = apiId;
    }
}
