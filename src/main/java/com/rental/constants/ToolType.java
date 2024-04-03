package com.rental.constants;

import java.util.Arrays;
import java.util.Optional;

public enum ToolType {

    Chainsaw("Chainsaw",10.50,true),
    Ladder("Ladder",5.0,true),
    Jackhammer("Jackhammer",15.0,true);

    private final String toolType;

    private final double dailyRentalCharge;

    private final boolean isWeekendsChargable;

    ToolType(String toolType, double dailyRentalCharge, boolean isWeekendsChargable) {
        this.toolType = toolType;
        this.dailyRentalCharge = dailyRentalCharge;
        this.isWeekendsChargable = isWeekendsChargable;
    }

    public String getToolType() {
        return toolType;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public boolean isWeekendsChargable() {
        return isWeekendsChargable;
    }

    public static Optional<ToolType> getToolRentalByToolType(String value) {
        return Arrays.stream(ToolType.values())
                .filter(tooltype -> tooltype.toolType.equals(value))
                .findFirst();
    }
}
