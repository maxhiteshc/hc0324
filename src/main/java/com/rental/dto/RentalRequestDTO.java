package com.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalRequestDTO {

    private String toolCode;

    private int rentalDaysCount;

    private int discountPercent;

    private String checkoutDate;

}
