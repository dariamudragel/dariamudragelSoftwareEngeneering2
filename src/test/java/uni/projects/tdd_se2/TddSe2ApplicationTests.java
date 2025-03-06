package uni.projects.tdd_se2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uni.projects.tdd_se2.controllers.CalculatorController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TddSe2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test()
    @DisplayName("Test empty expression")
    void testEmptyExpression() throws Exception {
        //Given
        String expression = "";
        Integer expectedResult = 0;

        //When
        MvcResult mvcResult = mockMvc.perform(post("/calculate"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test single number")
    void testSingleNumber() throws Exception {

        //Given
        String expression = "5";
        Integer expectedResult = 5;

        //When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test single number of multiple digits")
    void testSingleNumberMultipleDigits() throws Exception {

        //Given
        String expression = "123";
        Integer expectedResult = 123;

        //When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test addition comma delimated")
    void testAddition() throws Exception {

        //Given
        String expression = "5,5";
        Integer expectedResult = 10;

        //When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test addition newline delimated")
    void testAdditionNewline() throws Exception {

        //Given
        String expression = "5\n5";
        Integer expectedResult = 10;

        //When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        //Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test three numbers delimited by comma or newline return the sum")
    void testThreeNumbersDelimited() throws Exception {

        // Given
        String expression = "1,2\n3";
        Integer expectedResult = 6;

        // When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test single negative number throws an exception")
    void testSingleNegativeNumberThrowsException() throws Exception {

        // Given
        String expression = "-5";

        // When
        MvcResult mvc = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertNotNull(mvc.getResolvedException());
        assertEquals("Negative numbers are not allowed", mvc.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test addition with negative number throws an exception")
    void testAdditionWithNegativeNumberThrowsException() throws Exception {

        // Given
        String expression = "5,-3";

        // When
        MvcResult mvc = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertNotNull(mvc.getResolvedException());
        assertEquals("Negative numbers are not allowed", mvc.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Numbers greater than 1000 should be ignored")
    void testNumbersGreaterThan1000() throws Exception {

        // Given
        String expression = "1001,2";
        Integer expectedResult = 2;

        // When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Numbers equal to 1000 should be excluded")
    void testNumbersGreaterThan10002() throws Exception {

        // Given
        String expression = "1001";
        Integer expectedResult = 0;

        // When
        MvcResult mvcResult = mockMvc.perform(post("/calculate")
                        .content(expression)
                        .contentType("application/json"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Integer result = Integer.valueOf(content);

        // Then
        assertEquals(expectedResult, result);
    }

}