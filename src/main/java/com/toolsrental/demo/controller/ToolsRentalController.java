package com.toolsrental.demo.controller;


import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.dto.ToolsRentalResponseDTO;
import com.toolsrental.demo.service.impl.ToolsRentalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/checkout")
public class ToolsRentalController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ToolsRentalServiceImpl toolsRentalService;

    @PostMapping
    public ResponseEntity<ToolsRentalResponseDTO> getAllProducts(ToolsRentalRequestDTO toolsRentalRequestDTO) throws URISyntaxException {
        ToolsRentalResponseDTO toolsRentalResponseDTO = toolsRentalService.checkout(toolsRentalRequestDTO));
        printRentalAgreement(toolsRentalResponseDTO);
        return ResponseEntity.ok(toolsRentalResponseDTO);

        //// TODO: 28/03/24 Junits to pass Six Test cases provided
    }

    private void printRentalAgreement(ToolsRentalResponseDTO toolsRentalResponseDTO) {
        String newLine = System.getProperty("line.separator");
        String currency = "$";
        String percent = "%";
        //todo Format dates to // Date mm/dd/yy
        System.out.println("Tool code: " + toolsRentalResponseDTO.getToolCode() + newLine +
                        "Tool type: " + toolsRentalResponseDTO.getToolType() + newLine +
                        "Tool brand: " + toolsRentalResponseDTO.getToolBrand() + newLine +
                        "Rental days: " + toolsRentalResponseDTO.getRentalDaysCount() + newLine +
                        "Check out date: " + toolsRentalResponseDTO.getCheckoutDate() + newLine +
                        "Due date: " + toolsRentalResponseDTO.getDueDate() + newLine +
                        "Daily rental charge: " + currency + toolsRentalResponseDTO.getDailyRentalCharge() + newLine +
                        "Charge days: " + toolsRentalResponseDTO.getChargeDays() + newLine +
                        "Pre-discount charge: " + currency + toolsRentalResponseDTO.getPreDiscountCharge() + newLine +
                        "Discount percent: " + toolsRentalResponseDTO.getDiscountPercent() + percent + newLine +
                        "Discount amount: " + currency + toolsRentalResponseDTO.getDiscountAmount() + newLine +
                        "Final charge: " + currency + toolsRentalResponseDTO.getFinalCharge());
    }

}
