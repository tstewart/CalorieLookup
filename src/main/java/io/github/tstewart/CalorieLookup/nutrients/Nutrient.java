package io.github.tstewart.CalorieLookup.nutrients;

/**
 * Author: Thomas Stewart
 */
public class Nutrient {
    /**
     * Quantity of the specified nutrient
     */
    private double amount = 0.0;
    /**
     * Nutrition code (e.g. calorie nutrition code is CAL)
     */
    private String ntrCode = null;

    Nutrient(final double amount, final String ntrCode) {
        super();
        this.amount = amount;
        this.ntrCode = ntrCode;
    }

    Nutrient() {
        super();
    }

    @Override
    public String toString() {
        return this.getNtrCode() + ": " + this.getAmount();
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public String getNtrCode() {
        return this.ntrCode;
    }

    public void setNtrCode(final String ntrCode) {
        this.ntrCode = ntrCode;
    }
}

