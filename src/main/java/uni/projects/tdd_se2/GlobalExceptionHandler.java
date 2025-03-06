package uni.projects.tdd_se2;

/**
 * @author Tomasz Zbroszczyk
 * @version 1.0
 * @since 27.02.2025
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
