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
        VacationPayRequestDto requestDto = new VacationPayRequestDto();
        requestDto.setStartVacationDate(LocalDate.of(2024, 9, 1));
        requestDto.setVacationDays(10);
        requestDto.setAverageSalary(3000.0);

        when(dateUtil.isHoliday(any(LocalDate.class))).thenReturn(false);
        when(dateUtil.isWeekend(any(LocalDate.class))).thenReturn(false);

        double result = calculatorService.calculateVacationPay(requestDto);

        assertEquals(10 * 3000.0 / 29.5, result);
    }


}
