package com.rental.constants;

import java.util.Arrays;
import java.util.Optional;

public enum Tools {

    CHNS("CHNS","Chainsaw","Stihl"),
    LADW("LADW","Ladder","Werner"),
    JAKD("JAKD","Jackhammer","DeWalt"),
    JAKR("JAKR","Jackhammer","Ridgid");

    private final String toolCode;

    private final String toolType;

    private final String brand;

    Tools(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public static Optional<Tools> getToolsByToolCode(String value) {
        return Arrays.stream(Tools.values())
                .filter(tools -> tools.toolCode.equals(value))
                .findFirst();
    }
}
