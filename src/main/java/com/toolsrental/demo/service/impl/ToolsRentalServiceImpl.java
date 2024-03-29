package com.toolsrental.demo.service.impl;

import com.toolsrental.demo.constants.ToolType;
import com.toolsrental.demo.constants.Tools;
import com.toolsrental.demo.dto.ToolsRentalRequestDTO;
import com.toolsrental.demo.dto.ToolsRentalResponseDTO;
import com.toolsrental.demo.service.ToolsRentalService;


import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Arrays;

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

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate = LocalDate.parse(toolsRentalRequestDTO.getCheckoutDate(),df);
        LocalDate dueDate = checkoutDate.plusDays(toolsRentalRequestDTO.getRentalDaysCount());
        toolsRentalResponseDTO.setDueDate(dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

        ToolType toolType = ToolType.getToolRentalByToolType(toolsRentalResponseDTO.getToolType()).get();
        double dailyRentalCharge = toolType.getDailyRentalCharge();
        boolean isWeekendsChargeable = toolType.isWeekendsChargable();
        toolsRentalResponseDTO.setDailyRentalCharge(dailyRentalCharge);

        long weekends = 0;
        if(!isWeekendsChargeable){
            weekends = getWeekendsCount(checkoutDate, dueDate);
        }
        long chargeDays = toolsRentalRequestDTO.getRentalDaysCount() - getHolidaysCount(checkoutDate, dueDate) - weekends;
        toolsRentalResponseDTO.setChargeDays(chargeDays);

        double preDiscountChargeActual = dailyRentalCharge * chargeDays;
        double preDiscountCharge = (double) Math.round(preDiscountChargeActual * 100) / 100;
        toolsRentalResponseDTO.setPreDiscountCharge(preDiscountCharge);

        int discountPercent = toolsRentalRequestDTO.getDiscountPercent();


        if(discountPercent < 0 || discountPercent > 100 ) {
            throw new IllegalArgumentException("Discount percent is not in the range 0-100");
        }
        toolsRentalResponseDTO.setDiscountPercent(discountPercent);

        double discountAmountActual = (discountPercent*preDiscountCharge)/100;
        double discountAmount = (double) Math.round(discountAmountActual * 100) / 100;
        toolsRentalResponseDTO.setDiscountAmount(discountAmount);

        double finalCharge = preDiscountCharge - discountAmount;
        toolsRentalResponseDTO.setFinalCharge(finalCharge);

        return toolsRentalResponseDTO;
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
        return !laborDayHoliday.isBefore(checkoutDate) && !laborDayHoliday.isAfter(dueDate);
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
        return !independenceDayHoliday.isBefore(checkoutDate) && !independenceDayHoliday.isAfter(dueDate);
    }
}
