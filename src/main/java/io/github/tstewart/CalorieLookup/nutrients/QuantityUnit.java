package io.github.tstewart.CalorieLookup.nutrients;

enum QuantityUnit {

    GRAM (1, "g"),
    MILLIGRAM (0.001, "mg"),

    ;

    private final double quantityMulti;
    private final String quantityIdentifier;

    QuantityUnit(double quantityMulti, String quantityIdentifier) {
        this.quantityMulti = quantityMulti;
        this.quantityIdentifier = quantityIdentifier;
    }
}
