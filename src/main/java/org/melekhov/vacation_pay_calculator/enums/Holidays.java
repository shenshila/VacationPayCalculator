package org.melekhov.vacation_pay_calculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Month;

@Getter
public enum Holidays {

    NEW_YEAR(Month.JANUARY, 1, 2, 3, 4, 5, 6),
    CHRISTMAS(Month.JANUARY, 7),
    DEFENDERS_DAY(Month.FEBRUARY, 23),
    WOMEN_DAY(Month.MARCH, 8),
    LABOR_DAY(Month.MAY, 1),
    VICTORY_DAY(Month.MAY, 9),
    INDEPENDENCE_DAY(Month.JUNE, 12),
    NATIONAL_UNITY_DAY(Month.NOVEMBER, 4),;

    private final Month month;
    private final int[] days;

    Holidays(Month month, int... days) {
        this.month = month;
        this.days = days;
    }

    public boolean isDayExist(int day) {
        for (Integer tempDay : days) {
            if (tempDay == day) {
                return true;
            }
        }
        return false;
    }
}
