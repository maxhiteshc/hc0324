package com.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalResponseDTO {

    private String toolCode;

    private String toolType;

    private String toolBrand;

    private int rentalDaysCount;

    private String checkoutDate;

    private String dueDate;

    private double dailyRentalCharge;

    private long chargeDays;

    private double preDiscountCharge;

    private int discountPercent;

    private double discountAmount;

    private double finalCharge;
}
