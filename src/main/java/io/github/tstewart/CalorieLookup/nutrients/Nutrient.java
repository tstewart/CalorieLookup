package io.github.tstewart.CalorieLookup.nutrients;

public class Nutrient {
    /**
     * Quantity of the specified nutrient
     */
    private double amount;
    /**
     * Nutrition code (e.g. calorie nutrition code is CAL)
     */
    private String ntrCode;

    public Nutrient(double amount, String ntrCode) {
        this.amount = amount;
        this.ntrCode = ntrCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNtrCode() {
        return ntrCode;
    }

    public void setNtrCode(String ntrCode) {
        this.ntrCode = ntrCode;
    }
}

