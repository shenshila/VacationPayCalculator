package org.melekhov.vacation_pay_calculator.service.impl;

import org.melekhov.vacation_pay_calculator.enums.Holidays;
import org.melekhov.vacation_pay_calculator.service.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Service
public class DateUtilsImpl implements DateUtils {

    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isHoliday(LocalDate date) {
        return Arrays.stream(Holidays.values())
                .anyMatch(weekend -> weekend.getMonth() == date.getMonth() && weekend.isDayExist(date.getDayOfMonth()));
    }

}
