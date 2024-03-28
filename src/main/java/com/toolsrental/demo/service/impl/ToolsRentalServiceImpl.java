package com.toolsrental.demo.service.impl;

import com.toolsrental.demo.constants.ToolType;
import com.toolsrental.demo.constants.Tools;
import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.dto.ToolsRentalResponseDTO;
import com.toolsrental.demo.service.ToolsRentalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToolsRentalServiceImpl implements ToolsRentalService {
    public ToolsRentalResponseDTO checkout(ToolsRentalRequestDTO toolsRentalRequestDTO) {
        ToolsRentalResponseDTO toolsRentalResponseDTO = new ToolsRentalResponseDTO();
        toolsRentalResponseDTO.setToolCode(toolsRentalRequestDTO.getToolCode());

        Tools tools = Tools.getToolsByToolCode(toolsRentalRequestDTO.getToolCode());
        toolsRentalResponseDTO.setToolType(tools.getToolType());
        toolsRentalResponseDTO.setToolBrand(tools.getBrand());

        toolsRentalResponseDTO.setRentalDaysCount(toolsRentalRequestDTO.getRentalDaysCount());
        toolsRentalResponseDTO.setCheckoutDate(toolsRentalRequestDTO.getCheckoutDate());

        LocalDate checkoutDate = LocalDate.parse(toolsRentalRequestDTO.getCheckoutDate());
        LocalDate dueDate = checkoutDate.plusDays(toolsRentalRequestDTO.getRentalDaysCount());
        toolsRentalResponseDTO.setDueDate(dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        ToolType toolType = ToolType.getToolRentalByToolType(toolsRentalResponseDTO.getToolType());
        double dailyRentalCharge = toolType.getDailyRentalCharge();
        toolsRentalResponseDTO.setDailyRentalCharge(dailyRentalCharge);

        //// TODO: 28/03/24 Subtract non-chargeable days for holidays and weekends if applicable

        int chargeDays = toolsRentalRequestDTO.getRentalDaysCount();
        toolsRentalResponseDTO.setChargeDays(chargeDays);

        double preDiscountCharge = dailyRentalCharge * chargeDays;
        toolsRentalResponseDTO.setPreDiscountCharge(preDiscountCharge);

        int discountPercent = toolsRentalResponseDTO.getDiscountPercent();
        toolsRentalResponseDTO.setDiscountPercent(discountPercent);

        //// TODO: 28/03/24 Round it to cents
        double discountAmount = (discountPercent/100)* preDiscountCharge;
        toolsRentalResponseDTO.setDiscountAmount(discountAmount);

        double finalCharge = preDiscountCharge - discountAmount;
        toolsRentalResponseDTO.setFinalCharge(finalCharge);

        // TODO: 28/03/24 Generate and calculate Response values using request

        return toolsRentalResponseDTO;
    }
}
