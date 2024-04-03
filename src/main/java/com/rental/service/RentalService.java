package com.rental.service;

import com.rental.dto.RentalResponseDTO;
import com.rental.dto.RentalRequestDTO;

public interface RentalService {
    RentalResponseDTO checkout(RentalRequestDTO rentalRequestDTO);
}
