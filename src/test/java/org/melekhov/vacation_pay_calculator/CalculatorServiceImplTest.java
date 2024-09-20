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
    void calculateVacationPay_withDate() {
        VacationPayRequestDto request = new VacationPayRequestDto(78000, 14, LocalDate.of(2024, 12, 28));

        Mockito.when(dateUtil.isHoliday(LocalDate.of(2022, 1, 1))).thenReturn(true);
        Mockito.when(dateUtil.isWeekend(LocalDate.of(2022,1, 2))).thenReturn(true);
        when(dateUtil.isHoliday(Mockito.any(LocalDate.class))).thenReturn(false);
        when(dateUtil.isWeekend(Mockito.any(LocalDate.class))).thenReturn(false);

        double expectedVacationPay = 78000 * 14 / 29.5;
        double actualVacationPay = calculatorService.calculateVacationPay(request);

        assertEquals(expectedVacationPay, actualVacationPay);
    }

    @Test
    public void testCalculateWithHolidaysAndWeekends() {
        VacationPayRequestDto vacationPayRequestDto = new VacationPayRequestDto();
        vacationPayRequestDto.setStartVacationDate(LocalDate.of(2024, 12, 28));
        vacationPayRequestDto.setVacationDays(10);
        vacationPayRequestDto.setAverageSalary(100000.0);

        when(dateUtil.isHoliday(LocalDate.of(2025, 1, 1))).thenReturn(true);
        when(dateUtil.isWeekend(LocalDate.of(2024, 12, 29))).thenReturn(true);
        when(dateUtil.isHoliday(Mockito.any(LocalDate.class))).thenReturn(true);
        when(dateUtil.isWeekend(Mockito.any(LocalDate.class))).thenReturn(true);

        double expectedVacationPay = 100000 * 5 / 29.5;
        double result = calculatorService.calculateVacationPay(vacationPayRequestDto);

        assertEquals(expectedVacationPay, result, 5);
    }

}
