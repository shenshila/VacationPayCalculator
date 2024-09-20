package org.melekhov.vacation_pay_calculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.melekhov.vacation_pay_calculator.dto.VacationPayRequestDto;
import org.melekhov.vacation_pay_calculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate (@Valid @RequestBody VacationPayRequestDto vacationPayRequestDto) {
        double result = calculatorService.calculateVacationPay(vacationPayRequestDto);
        return ResponseEntity.ok(result);
    }

}
