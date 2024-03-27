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
        return ResponseEntity.ok(toolsRentalService.checkout(toolsRentalRequestDTO));
    }

}
