package org.melekhov.vacation_pay_calculator.service;

import org.melekhov.vacation_pay_calculator.models.VacationPayRequest;

public interface CalculatorService {

    double calculateVacationPay(VacationPayRequest vacationPayRequest);
//    double calculateVacationPay(Double averageSalary, Double vacationDays);

}
