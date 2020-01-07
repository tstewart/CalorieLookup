package io.github.tstewart.CalorieLookup.nutrients;

public class Nutrient {
    /**
     * Quantity of the specified nutrient
     */
    private int amount;
    /**
     * Nutrition code (e.g. calorie nutrition code is CAL)
     */
    private String ntrCode;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNtrCode() {
        return ntrCode;
    }

    public void setNtrCode(String ntrCode) {
        this.ntrCode = ntrCode;
    }
}

class Carbohydrates extends Nutrient {

}

class Fat extends Nutrient {

}

class Energy extends Nutrient {

}

class Protein extends Nutrient {

}