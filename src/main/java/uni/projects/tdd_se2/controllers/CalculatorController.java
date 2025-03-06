package uni.projects.tdd_se2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Zbroszczyk
 * @version 1.0
 * @since 27.02.2025
 */

@RestController
public class CalculatorController {

    @PostMapping("/calculate")
    public Integer calculate(@RequestBody(required = false) String expression) {

        if (expression == null || expression.isEmpty())
            return 0;

        String delimiter = "[,\n]";
        if (expression.startsWith("#\n")) {
            int delimiterIndex = expression.indexOf("\n");
            delimiter = expression.substring(1, delimiterIndex);
            expression = expression.substring(delimiterIndex + 1);
        }

        String[] numbers = expression.split(delimiter);
        int sum = 0;
        for (String number : numbers) {
            int numberInt = Integer.parseInt(number);
            if (numberInt > 1000) {
                continue;
            }
            if (numberInt < 0) {
                throw new IllegalArgumentException("Negative numbers are not allowed");
            }
            sum += numberInt;
        }

        return sum;
    }
}
