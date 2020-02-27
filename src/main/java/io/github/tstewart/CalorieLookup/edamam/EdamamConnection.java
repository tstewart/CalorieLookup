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

/**
 * Author: Thomas Stewart
 */
public class EdamamConnection extends Connection {

    // URL prefix for food searches
    private final String edamamFoodSearchUrl = "https://api.edamam.com/api/food-database/parser?";
    // URL p0refix for recipe searches
    private final String edamamRecipeSearchUrl = "https://api.edamam.com/search?";
    // Format of API id and key variables, used in conjunction with String.format()
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
        /**
         * Check that the variables provided by the function are valid
         */
        // If the request object is null, throw an invalid request exception as this is required.
        if (null == request) throw new InvalidRequestException("Please specify a valid APIRequest");
        // If the API key or Id is null, throw an invalid request exception
        if (null == apiKey || null == apiId)
            throw new InvalidRequestException("A valid API id and key must be provided.");

        final Request requestType = request.getRequest();
        final String requestUrl;

        /**
         * Check that the request type is supported.
         * In this case, only instances of Food/Recipe requests are supported.
         */
        if (requestType instanceof FoodRequest) {
            // Generate a formatted food request URL to send to Edamam.
            requestUrl = this.createFormattedFoodString(((FoodRequest) requestType).getFoodRequest());
        } else if (requestType instanceof RecipeRequest) {
            // Generate a formatted recipe request URL to send to Edamam.
            requestUrl = this.createFormattedRecipeString((RecipeRequest) requestType);
        } else {
            // If the object is not of type FoodRequest or RecipeRequest, it is not supported.
            throw new InvalidRequestException("Request type is not supported.");
        }

        try {
            // Try and get a JSON response from the parsed URL
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

    /**
     * Creates a formatted food URL request for Edamam.
     * @param food Food request string (e.g. Apple, Pizza, etc).
     * @return A formatted URL string.
     */
    private String createFormattedFoodString(final String food) {
        // Replace certain characters with their HTML encoded substitute, to prevent HTML errors when contacting Edamam.
        final String foodNameFormatted = this.encode(food);

        // Return a formatted URL that Edamam can respond to.
        return String.format(this.edamamFoodSearchUrl + this.apiParamFormat + "&ingr=%s", this.apiId, this.apiKey, foodNameFormatted);
    }

    /**
     * Creates a formatted recipe URL request for Edamam.
     * @param recipeRequest Recipe request, containing the nutrients of the user and the ingredient they want in their recipe.
     * @return A formatted URL string.
     */
    private String createFormattedRecipeString(final RecipeRequest recipeRequest) {
        // Encodes the food request, removing characters that can cause issues such as spaces and quotation marks.
        final String recipeQuery = this.encode(recipeRequest.getFoodQuery());
        // The meal type returned by this function is always a dinner recipe, as to fit with the theme of the parent application.
        final String mealTypeOverride = "mealtype=dinner";

        final StringBuilder nutrients = new StringBuilder();
        // Add each nutrient target to the query. This is added as a maximum value (e.g. if you have 10grams of fat left in your daily nutrition requirements, 10g is set as the max amount of fat the recipe can contain),
        recipeRequest.getTargetNutrients().forEach((nutrient -> {
            // If the nutrient amount is not negative.
            if (nutrient.getAmount() > 0) {
                nutrients.append("nutrients[").append(nutrient.getNtrCode()).append("]=").append((int) Math.floor(nutrient.getAmount())).append("&");
            }
        }));
        // Encode the nutrients request we just compiled, to fit with URL requirements.
        final String nutrientsEncoded = this.encode(nutrients.toString());
        // Calorie target for the user. Added in a similar fashion to nutrients, the calories in the request are the maximum number of calories the recipe can contain.
        final int targetCalories = recipeRequest.getTargetCalories();

        // If the target calories are greater than 0, add them. If not they can be left out of the request.
        if(targetCalories > 0) {
            nutrients.append("calories=").append(targetCalories);
        }

        // Return a formatted URL that Edamam can respond to.
        return String.format(this.edamamRecipeSearchUrl + this.apiParamFormat + "&q=%s&%s&%s", this.apiId, this.apiKey, recipeQuery, mealTypeOverride, nutrientsEncoded);
    }

    /**
     * Encodes specific characters to ensure that Edamam can support such requests.
     * Only certain characters are encoded as per the requirements for Edamam's URL request
     * @param string String to be encoded
     * @return Encoded string
     */
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
