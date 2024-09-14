package org.melekhov.vacation_pay_calculator.service;

import java.time.LocalDate;

public interface DateUtils {

    boolean isWeekend(LocalDate date);
    boolean isHoliday(LocalDate date);

}
