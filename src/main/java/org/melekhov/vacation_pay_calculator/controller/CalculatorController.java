package org.melekhov.vacation_pay_calculator.controller;

import lombok.RequiredArgsConstructor;
import org.melekhov.vacation_pay_calculator.models.VacationPayRequest;
import org.melekhov.vacation_pay_calculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Приложение "Калькулятор отпускных".
//Микросервис на SpringBoot + Java 11 c одним API:
//GET "/calculacte"
//
//Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных,
//которые придут сотруднику.

//Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных.
//
//Проверяться будет чистота кода, структура проекта, название полей\классов, правильность использования паттернов.
//Желательно написание юнит-тестов, проверяющих расчет.

@RestController
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate (@RequestBody VacationPayRequest vacationPayRequest) {
        double result = calculatorService.calculateVacationPay(vacationPayRequest);
        return ResponseEntity.ok(result);
    }

}
