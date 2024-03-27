package com.toolsrental.demo.service;

import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.dto.ToolsRentalResponseDTO;

public interface ToolsRentalService {
    ToolsRentalResponseDTO checkout(ToolsRentalRequestDTO toolsRentalRequestDTO);
}
