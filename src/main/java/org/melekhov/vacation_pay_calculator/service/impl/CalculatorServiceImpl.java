package org.melekhov.vacation_pay_calculator.service.impl;

import lombok.RequiredArgsConstructor;
import org.melekhov.vacation_pay_calculator.dto.VacationPayRequestDto;
import org.melekhov.vacation_pay_calculator.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    private static final double AVG_MONTH_DAYS = 29.5;
    private final DateUtilsImpl dateUtils;

    @Override
    public double calculateVacationPay(VacationPayRequestDto vacationPayRequestDto) {

        if (vacationPayRequestDto.getStartVacationDate() != null)
            return calculateWithDate(vacationPayRequestDto);
        else
            return calculateWithoutDate(vacationPayRequestDto);

    }

    private double calculateWithoutDate(VacationPayRequestDto vacationPayRequestDto) {
        return vacationPayRequestDto.getAverageSalary() / AVG_MONTH_DAYS * vacationPayRequestDto.getVacationDays();
    }

    private double calculateWithDate(VacationPayRequestDto vacationPayRequestDto) {
        LocalDate startVacationDate = vacationPayRequestDto.getStartVacationDate();
        int vacationDays = vacationPayRequestDto.getVacationDays();

        for (int i = 0; i < vacationDays; i++) {
            if (dateUtils.isHoliday(startVacationDate) || dateUtils.isWeekend(startVacationDate)) {
                vacationDays--;
            }
            startVacationDate = startVacationDate.plusDays(1);
        }

        return vacationDays * vacationPayRequestDto.getAverageSalary() / AVG_MONTH_DAYS;
    }
}
