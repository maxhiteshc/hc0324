package com.toolsrental.demo.constants;

public enum ToolType {

    Chainsaw("Chainsaw",10.50,true),
    Ladder("Ladder",5.0,true),
    Jackhammer("Jackhammer",15.0,true);

    private final String toolCode;

    private final double dailyRentalCharge;

    private final boolean isWeekendsChargable;

    ToolType(String toolCode, double dailyRentalCharge, boolean isWeekendsChargable) {
        this.toolCode = toolCode;
        this.dailyRentalCharge = dailyRentalCharge;
        this.isWeekendsChargable = isWeekendsChargable;
    }

    public String getToolCode() {
        return toolCode;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public boolean isWeekendsChargable() {
        return isWeekendsChargable;
    }
}
