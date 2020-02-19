package io.github.tstewart.CalorieLookup;

import io.github.tstewart.CalorieLookup.nutrients.Nutrient;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    /**
     * Recipe name
     */
    private String name;

    private String iconUrl;

    private String recipeUrl;

    public Recipe(String name, String iconUrl, String recipeUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.recipeUrl = recipeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }

    @Override
    public String toString() {
        return name;
    }
}
