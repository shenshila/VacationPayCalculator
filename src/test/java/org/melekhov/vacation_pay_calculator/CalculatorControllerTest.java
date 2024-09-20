package org.melekhov.vacation_pay_calculator;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melekhov.vacation_pay_calculator.controller.CalculatorController;
import org.melekhov.vacation_pay_calculator.dto.VacationPayRequestDto;
import org.melekhov.vacation_pay_calculator.service.CalculatorService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculatorController.class)
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    @SuppressWarnings("null")
    public void calculateVacationPay_WithValidRequest_ShouldReturnOk() throws Exception {
        VacationPayRequestDto request = new VacationPayRequestDto(160000,14,null);
        double expectedResult = 16000.0 * 14 / 29.3;


        Mockito.when(calculatorService.calculateVacationPay(request))
                .thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @SuppressWarnings("null")
    @Test
    void calculateVacationPay_WithInvalidRequest_ShouldReturnBadRequest() throws Exception {
        VacationPayRequestDto request = new VacationPayRequestDto(-500.0, 0, null);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


}
