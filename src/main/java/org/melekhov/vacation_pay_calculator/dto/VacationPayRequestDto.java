package org.melekhov.vacation_pay_calculator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationPayRequestDto {

    @NotNull(message = "Salary must be specified")
    @Min(value = 10000, message = "Salary must be greater than 10000")
    private double averageSalary;

    @NotNull(message = "Vacation days must be specified")
    @Min(value = 7, message = "Vacation days must be greater than 6")
    private int vacationDays;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startVacationDate;

}
