package org.melekhov.vacation_pay_calculator.service.impl;

import lombok.RequiredArgsConstructor;
import org.melekhov.vacation_pay_calculator.models.VacationPayRequest;
import org.melekhov.vacation_pay_calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    private static final double AVG_MONTH_DAYS = 29.5;
    private final DateUtilsImpl dateUtils;

    @Override
    public double calculateVacationPay(VacationPayRequest vacationPayRequest) {

        if (vacationPayRequest.getStartVacationDate() != null)
            return calculateWithDate(vacationPayRequest);
        else
            return calculateWithoutDate(vacationPayRequest);

    }

    private double calculateWithoutDate(VacationPayRequest vacationPayRequest) {
        return vacationPayRequest.getAverageSalary() / AVG_MONTH_DAYS * vacationPayRequest.getVacationDays();
    }

    private double calculateWithDate(VacationPayRequest vacationPayRequest) {
        LocalDate startVacationDate = vacationPayRequest.getStartVacationDate();
        int vacationDays = vacationPayRequest.getVacationDays();

        for (int i = 0; i < vacationDays; i++) {
            if (dateUtils.isHoliday(startVacationDate) || dateUtils.isWeekend(startVacationDate)) {
                vacationDays--;
            }
            startVacationDate = startVacationDate.plusDays(1);
        }

        return vacationDays * vacationPayRequest.getAverageSalary() / AVG_MONTH_DAYS;
    }



//    @Value("$app.average-month-days")
//    private double AVERAGE_MONTHS_DAYS;

//    public double calculateVacationPay(Double averageSalary, Double vacationDays) {
//        return calculate(averageSalary, vacationDays);
//    }
//
//    private double calculate(Double averageSalary, Double vacationDays) {
//        return averageSalary / AVERAGE_MONTHS_DAYS * vacationDays;
//    }
}
