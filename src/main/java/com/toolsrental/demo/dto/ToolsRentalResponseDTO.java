package com.toolsrental.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToolsRentalResponseDTO {

    private String toolCode;

    private String toolType;

    private String toolBrand;

    private int rentalDaysCount;

    private String checkoutDate;

    private String dueDate;

    private double dailyRentalCharge;

    private int chargeDays;

    private double preDiscountCharge;

    private int discountPercent;

    private double discountAmount;

    private double finalCharge;
}
