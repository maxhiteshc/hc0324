package com.rental.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.dto.RentalRequestDTO;
import com.rental.dto.RentalResponseDTO;
import com.rental.service.impl.RentalServiceImpl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;


/* NOTE THIS can be run from the main method for now at the bottom of the class */

@RestController
@RequestMapping("/v1/checkout")
public class RentalController {


    /*
    @Autowired
    private RentalServiceImpl RentalService;

    @Autowired
    ObjectMapper objectMapper;
    */

    @PostMapping
    public ResponseEntity<RentalResponseDTO> checkout(RentalRequestDTO rentalRequestDTO) throws IllegalArgumentException {
        RentalServiceImpl rentalService = new RentalServiceImpl();
        RentalResponseDTO rentalResponseDTO = rentalService.checkout(rentalRequestDTO);
        printRentalAgreement(rentalResponseDTO);
        return ResponseEntity.ok(rentalResponseDTO);
    }

    private void printRentalAgreement(RentalResponseDTO rentalResponseDTO) {
        String newLine = System.getProperty("line.separator");
        String currency = "$";
        String percent = "%";
        System.out.println("Tool code: " + rentalResponseDTO.getToolCode() + newLine +
                        "Tool type: " + rentalResponseDTO.getToolType() + newLine +
                        "Tool brand: " + rentalResponseDTO.getToolBrand() + newLine +
                        "Rental days: " + rentalResponseDTO.getRentalDaysCount() + newLine +
                        "Check out date: " + rentalResponseDTO.getCheckoutDate() + newLine +
                        "Due date: " + rentalResponseDTO.getDueDate() + newLine +
                        "Daily rental charge: " + currency + rentalResponseDTO.getDailyRentalCharge() + newLine +
                        "Charge days: " + rentalResponseDTO.getChargeDays() + newLine +
                        "Pre-discount charge: " + currency + rentalResponseDTO.getPreDiscountCharge() + newLine +
                        "Discount percent: " + rentalResponseDTO.getDiscountPercent() + percent + newLine +
                        "Discount amount: " + currency + rentalResponseDTO.getDiscountAmount() + newLine +
                        "Final charge: " + currency + rentalResponseDTO.getFinalCharge() + newLine);

    }

    public static void main(String[] args)  throws Exception {

        // Test Case 1
        checkout("test1.json");
        // Test Case 2
        checkout("test2.json");
        // Test Case 3
        checkout("test3.json");
        // Test Case 4
        checkout("test4.json");
        // Test Case 5
        checkout("test5.json");
        // Test Case 6
        checkout("test6.json");

    }

    private static void checkout(String testFilePath) throws IOException {
        ClassPathResource testFile = new ClassPathResource(testFilePath);
        String checkoutRequest = StreamUtils.copyToString( testFile.getInputStream(), Charset.defaultCharset());
        String newLine = System.getProperty("line.separator");
        ObjectMapper objectMapper = new ObjectMapper();
        RentalRequestDTO rentalRequestDTO = objectMapper.readValue(checkoutRequest, RentalRequestDTO.class);
        RentalController rentalController = new RentalController();
        try {
            System.out.println("Rental Agreement:");
            rentalController.checkout(rentalRequestDTO);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
