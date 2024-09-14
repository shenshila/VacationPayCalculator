package org.melekhov.vacation_pay_calculator.service.impl;

import org.melekhov.vacation_pay_calculator.enums.Holidays;
import org.melekhov.vacation_pay_calculator.service.DateUtils;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Component
public class DateUtilsImpl {

//    public static int workingDays(LocalDate startDate, int vacationDays) {
//        int workingDays = 0;
//        LocalDate date = startDate;
//
//        while (date.isBefore(startDate.plusDays(vacationDays))) {
//            if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
//                    date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) || date.equals(Holidays.valueOf(Holidays.class, )) {
//
//            }
//        }
//    }

    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isHoliday(LocalDate date) {
        return Arrays.stream(Holidays.values())
                .anyMatch(weekend -> weekend.getMonth() == date.getMonth() && weekend.isDayExist(date.getDayOfMonth()));
    }


//    @Override
//    public boolean isWeekend(LocalDate date) {
//        return false;
//    }
//
//    @Override
//    public boolean isHoliday(LocalDate date) {
//        return false;
//    }
}
