package com.toolsrental.demo.service.impl;

import com.toolsrental.demo.constants.ToolType;
import com.toolsrental.demo.constants.Tools;
import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.dto.ToolsRentalResponseDTO;
import com.toolsrental.demo.service.ToolsRentalService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.validation.method.MethodValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ToolsRentalServiceImpl implements ToolsRentalService {
    public ToolsRentalResponseDTO checkout(ToolsRentalRequestDTO toolsRentalRequestDTO) throws IllegalArgumentException {
        ToolsRentalResponseDTO toolsRentalResponseDTO = new ToolsRentalResponseDTO();
        toolsRentalResponseDTO.setToolCode(toolsRentalRequestDTO.getToolCode());

        Tools tools = Tools.getToolsByToolCode(toolsRentalRequestDTO.getToolCode()).get();
        toolsRentalResponseDTO.setToolType(tools.getToolType());
        toolsRentalResponseDTO.setToolBrand(tools.getBrand());

        if(toolsRentalRequestDTO.getRentalDaysCount() < 1 ) {
            throw new IllegalArgumentException("Rental day count is not 1 or greater");
        }
        toolsRentalResponseDTO.setRentalDaysCount(toolsRentalRequestDTO.getRentalDaysCount());
        toolsRentalResponseDTO.setCheckoutDate(toolsRentalRequestDTO.getCheckoutDate());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate checkoutDate = LocalDate.parse(toolsRentalRequestDTO.getCheckoutDate(),df);
        LocalDate dueDate = checkoutDate.plusDays(toolsRentalRequestDTO.getRentalDaysCount());
        toolsRentalResponseDTO.setDueDate(dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        ToolType toolType = ToolType.getToolRentalByToolType(toolsRentalResponseDTO.getToolType()).get();
        double dailyRentalCharge = toolType.getDailyRentalCharge();
        toolsRentalResponseDTO.setDailyRentalCharge(dailyRentalCharge);

        //// TODO: 28/03/24 Subtract non-chargeable days for holidays and weekends if applicable

        int chargeDays = toolsRentalRequestDTO.getRentalDaysCount();
        toolsRentalResponseDTO.setChargeDays(chargeDays);

        double preDiscountCharge = dailyRentalCharge * chargeDays;
        toolsRentalResponseDTO.setPreDiscountCharge(preDiscountCharge);

        int discountPercent = toolsRentalRequestDTO.getDiscountPercent();


        if(discountPercent < 0 || discountPercent > 100 ) {
            throw new IllegalArgumentException("Discount percent is not in the range 0-100");
        }
        toolsRentalResponseDTO.setDiscountPercent(discountPercent);

        //// TODO: 28/03/24 Round it to cents
        double discountAmount = (discountPercent*preDiscountCharge)/100;
        toolsRentalResponseDTO.setDiscountAmount(discountAmount);

        double finalCharge = preDiscountCharge - discountAmount;
        toolsRentalResponseDTO.setFinalCharge(finalCharge);

        return toolsRentalResponseDTO;
    }
}
