package com.toolsrental.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToolsRentalRequestDTO {

    private String toolCode;

    private int rentalDaysCount;

    private int discountPercent;

    private String checkoutDate;

}
