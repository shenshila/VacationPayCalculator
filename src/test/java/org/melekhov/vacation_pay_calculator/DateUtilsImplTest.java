package org.melekhov.vacation_pay_calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.melekhov.vacation_pay_calculator.service.DateUtils;
import org.melekhov.vacation_pay_calculator.service.impl.DateUtilsImpl;

import java.time.LocalDate;

public class DateUtilsImplTest {

    private final DateUtils dateUtils = new DateUtilsImpl();

    @Test
    void isWeekend_true() {
        LocalDate saturday = LocalDate.of(2024, 9, 14);
        LocalDate sunday = LocalDate.of(2024, 9, 15);

        Assertions.assertTrue(dateUtils.isWeekend(saturday));
        Assertions.assertTrue(dateUtils.isWeekend(sunday));
    }

    @Test
    void isWeekend_false() {
        LocalDate monday = LocalDate.of(2024, 9, 9);

        Assertions.assertFalse(dateUtils.isWeekend(monday));
    }

    @Test
    void isHoliday_true() {
        LocalDate christmas = LocalDate.of(2024, 1, 7);
        LocalDate womanDay = LocalDate.of(2024, 3, 8);

        Assertions.assertTrue(dateUtils.isHoliday(christmas));
        Assertions.assertTrue(dateUtils.isHoliday(womanDay));
    }

    @Test
    void isHoliday_false() {
        LocalDate randomDay = LocalDate.of(2024, 4, 4);
        LocalDate beerDay = LocalDate.of(2024, 8, 2);

        Assertions.assertFalse(dateUtils.isHoliday(randomDay));
        Assertions.assertFalse(dateUtils.isHoliday(beerDay));
    }

}
