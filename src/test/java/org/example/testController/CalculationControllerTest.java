package org.example.testController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Main;
import org.example.check.Check;
import org.example.check.CheckImpl;
import org.example.controlers.CalculationController;
import org.example.controlers.ExceptionController;
import org.example.pojo.FactorialNumber;
import org.example.service.Calculation;
import org.example.service.CalculationFactorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CalculationControllerTest {
    @Mock
    private Check check;
    private CheckImpl checkImpl = new CheckImpl();

    @Mock
    private Calculation calculation;
    private CalculationFactorial calculationFactorial = new CalculationFactorial();

    @InjectMocks
    private CalculationController calculationController;

    @InjectMocks
    private ExceptionController exceptionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(calculationController)
                .setControllerAdvice(exceptionController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void nullFactorial() throws Exception {
        Double factorial = null;
        String expectedValue = "Entered incorrect data, enter a number from 1 to 100 -" +
                " Cannot invoke \"java.lang.Double.equals(Object)\" because \"data\" is null";
        String errorMessage = "Cannot invoke \"java.lang.Double.equals(Object)\" because \"data\" is null";
        Mockito.doThrow(new IllegalArgumentException(errorMessage)).when(check).getCheck(factorial);

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(expectedValue));
    }

    @Test
    void factorialIsLess0() throws Exception {
        Double factorial = -1.;
        String expectedValue = "Entered incorrect data, enter a number from 1 to 100 - you passed an incorrect value";
        String errorMessage = "you passed an incorrect value";
        Mockito.doThrow(new IllegalArgumentException(errorMessage)).when(check).getCheck(factorial);

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(expectedValue));
    }

    @Test
    void factorialIsMore100() throws Exception {
        Double factorial = 101.;
        String expectedValue = "Entered incorrect data, enter a number from 1 to 100 - you passed an incorrect value";
        String errorMessage = "you passed an incorrect value";
        Mockito.doThrow(new IllegalArgumentException(errorMessage)).when(check).getCheck(factorial);

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(expectedValue));
    }

    @Test
    void goodRequestFirstResult() throws Exception {
        Double factorial = 3.;
        Double expectedValue = 6.;
        Mockito.when(check.getCheck(factorial)).thenReturn(checkImpl
                .getCheck(factorial));
        Mockito.when(calculation.calculationResult(factorial))
                .thenReturn(calculationFactorial.calculationResult(factorial));

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(jsonPath("result").value(expectedValue))
                .andExpect(status().isOk());
    }

    @Test
    void goodRequestSecondResult() throws Exception {
        Double factorial = 64.;
        Double expectedValue = 126886932185884164103433389335161480802865516174545192198801894375214704230400000000000000.;
        Mockito.when(check.getCheck(factorial)).thenReturn(checkImpl
                .getCheck(factorial));
        Mockito.when(calculation.calculationResult(factorial))
                .thenReturn(calculationFactorial.calculationResult(factorial));

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(jsonPath("result").value(expectedValue))
                .andExpect(status().isOk());
    }

    @Test
    void goodFactorialIsDouble() throws Exception {
        Double factorial = 5.5;
        Double expectedValue = 287.88527781504433;
        Mockito.when(check.getCheck(factorial)).thenReturn(checkImpl
                .getCheck(factorial));
        Mockito.when(calculation.calculationResult(factorial))
                .thenReturn(calculationFactorial.calculationResult(factorial));

        FactorialNumber factorialNumber = new FactorialNumber(factorial);
        String factorialJSON = objectMapper.writeValueAsString(factorialNumber);
        mockMvc.perform(post("/api/v1/calculator/factorial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(factorialJSON))
                .andExpect(jsonPath("result").value(expectedValue))
                .andExpect(status().isOk());
    }

}
