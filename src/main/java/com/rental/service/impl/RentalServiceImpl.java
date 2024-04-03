package com.rental.service.impl;

import com.rental.constants.ToolType;
import com.rental.constants.Tools;
import com.rental.dto.RentalRequestDTO;
import com.rental.dto.RentalResponseDTO;
import com.rental.service.RentalService;


import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Arrays;

public class RentalServiceImpl implements RentalService {
    public RentalResponseDTO checkout(RentalRequestDTO rentalRequestDTO) throws IllegalArgumentException {
        RentalResponseDTO rentalResponseDTO = new RentalResponseDTO();
        rentalResponseDTO.setToolCode(rentalRequestDTO.getToolCode());

        Tools tools = Tools.getToolsByToolCode(rentalRequestDTO.getToolCode()).get();
        rentalResponseDTO.setToolType(tools.getToolType());
        rentalResponseDTO.setToolBrand(tools.getBrand());

        if(rentalRequestDTO.getRentalDaysCount() < 1 ) {
            throw new IllegalArgumentException("Rental day count is not 1 or greater");
        }
        rentalResponseDTO.setRentalDaysCount(rentalRequestDTO.getRentalDaysCount());
        rentalResponseDTO.setCheckoutDate(rentalRequestDTO.getCheckoutDate());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate = LocalDate.parse(rentalRequestDTO.getCheckoutDate(),df);
        LocalDate dueDate = checkoutDate.plusDays(rentalRequestDTO.getRentalDaysCount());
        rentalResponseDTO.setDueDate(dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

        ToolType toolType = ToolType.getToolRentalByToolType(rentalResponseDTO.getToolType()).get();
        double dailyRentalCharge = toolType.getDailyRentalCharge();
        boolean isWeekendsChargeable = toolType.isWeekendsChargable();
        rentalResponseDTO.setDailyRentalCharge(dailyRentalCharge);

        long weekends = 0;
        if(!isWeekendsChargeable){
            weekends = getWeekendsCount(checkoutDate, dueDate);
        }
        long chargeDays = rentalRequestDTO.getRentalDaysCount() - getHolidaysCount(checkoutDate, dueDate) - weekends;
        rentalResponseDTO.setChargeDays(chargeDays);

        double preDiscountChargeActual = dailyRentalCharge * chargeDays;
        double preDiscountCharge = (double) Math.round(preDiscountChargeActual * 100.0) / 100.0;
        rentalResponseDTO.setPreDiscountCharge(preDiscountCharge);

        int discountPercent = rentalRequestDTO.getDiscountPercent();


        if(discountPercent < 0 || discountPercent > 100 ) {
            throw new IllegalArgumentException("Discount percent is not in the range 0-100");
        }
        rentalResponseDTO.setDiscountPercent(discountPercent);

        double discountAmountActual = (discountPercent*preDiscountCharge)/100.0;
        double discountAmount = (double) Math.round(discountAmountActual * 100.0) / 100.0;
        rentalResponseDTO.setDiscountAmount(discountAmount);

        double finalCharge = preDiscountCharge - discountAmount;
        rentalResponseDTO.setFinalCharge(finalCharge);

        return rentalResponseDTO;
    }

    private long getWeekendsCount(LocalDate checkoutDate, LocalDate dueDate) {
        return checkoutDate.datesUntil(dueDate)
                .map(LocalDate::getDayOfWeek)
                .filter(day -> Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(day))
                .count();
    }

    private int getHolidaysCount(LocalDate checkoutDate, LocalDate dueDate) {
        int holidays = 0;
        if(hasIndependenceDay(checkoutDate,dueDate)) {
            holidays++;
        }
        if(hasLaborDay(checkoutDate,dueDate)){
            holidays++;
        }
        return holidays;
    }

    private boolean hasLaborDay(LocalDate checkoutDate, LocalDate dueDate) {
        LocalDate laborMonth1stDay = LocalDate.of(checkoutDate.getYear(), 9, 1);
        LocalDate laborDayHoliday = laborMonth1stDay.with(firstInMonth(DayOfWeek.MONDAY));
        return !laborDayHoliday.isBefore(checkoutDate) && !laborDayHoliday.isAfter(dueDate.minusDays(1));
    }

    private boolean hasIndependenceDay(LocalDate checkoutDate, LocalDate dueDate) {
        LocalDate independenceDay = LocalDate.of(checkoutDate.getYear(), 7, 4);
        LocalDate independenceDayHoliday;
        DayOfWeek day = DayOfWeek.of(independenceDay.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SATURDAY) {
            independenceDayHoliday = independenceDay.minusDays(1);
        } else if(day == DayOfWeek.SUNDAY){
            independenceDayHoliday = independenceDay.plusDays(1);
        } else {
            independenceDayHoliday = independenceDay;
        }
        return !independenceDayHoliday.isBefore(checkoutDate) && !independenceDayHoliday.isAfter(dueDate.minusDays(1));
    }
}
