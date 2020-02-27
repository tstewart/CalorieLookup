package io.github.tstewart.CalorieLookup;

import java.io.Serializable;

/**
 * Author: Thomas Stewart
 */
public class Recipe implements Serializable {
    /**
     * Recipe name
     */
    private String name;

    private String iconUrl;

    private String recipeUrl;

    public Recipe(final String name, final String iconUrl, final String recipeUrl) {
        super();
        this.name = name;
        this.iconUrl = iconUrl;
        this.recipeUrl = recipeUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(final String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getRecipeUrl() {
        return this.recipeUrl;
    }

    public void setRecipeUrl(final String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
