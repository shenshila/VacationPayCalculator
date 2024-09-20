package org.melekhov.vacation_pay_calculator.service;

import org.melekhov.vacation_pay_calculator.dto.VacationPayRequestDto;

public interface CalculatorService {

    double calculateVacationPay(VacationPayRequestDto vacationPayRequestDto);
//    double calculateVacationPay(Double averageSalary, Double vacationDays);

}
