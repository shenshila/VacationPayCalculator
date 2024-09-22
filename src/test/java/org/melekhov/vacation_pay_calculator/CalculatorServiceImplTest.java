package org.melekhov.vacation_pay_calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melekhov.vacation_pay_calculator.dto.VacationPayRequestDto;
import org.melekhov.vacation_pay_calculator.service.impl.CalculatorServiceImpl;
import org.melekhov.vacation_pay_calculator.service.impl.DateUtilsImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceImplTest {

    @InjectMocks
    private CalculatorServiceImpl calculatorService;

    @Mock
    private DateUtilsImpl dateUtil;

    @Test
    void calculateVacationPay_withoutDate() {
        VacationPayRequestDto request = new VacationPayRequestDto(78000, 14, null);

        double expectedVacationPay = 78000 * 14 / 29.5;

        double actualVacationPay = calculatorService.calculateVacationPay(request);

        assertEquals(expectedVacationPay, actualVacationPay);
    }

    @Test
    public void testCalculateWithHolidays() {
        VacationPayRequestDto request = new VacationPayRequestDto();
        request.setAverageSalary(10000);
        request.setVacationDays(7);
        request.setStartVacationDate(LocalDate.of(2024, 12, 29));


        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2024, 12, 29))).thenReturn(true);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2024, 12, 30))).thenReturn(false);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2024, 12, 31))).thenReturn(false);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2025, 1, 1))).thenReturn(true);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2025, 1, 2))).thenReturn(true);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2025, 1, 3))).thenReturn(true);
        when(calculatorService.isWeekendOrHoliday(LocalDate.of(2025, 1, 4))).thenReturn(true);

        double expectedVacationPay = 10000 * 2 / 29.5;
        double actualVacationPay = calculatorService.calculateVacationPay(request);

        assertEquals(expectedVacationPay, actualVacationPay);
    }


}
