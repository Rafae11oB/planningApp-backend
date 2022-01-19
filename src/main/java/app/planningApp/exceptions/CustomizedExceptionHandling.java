package app.planningApp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserFriendlyException.class)
    public ResponseEntity<Map<String, String>> handleUserFriendlyExceptions(UserFriendlyException exception){
        return ResponseEntity.badRequest().body(Map.of("msg", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleDeveloperExceptions(Exception exception){
        log.error("{}: {}", LocalDateTime.now(), exception.getMessage());
        return ResponseEntity.badRequest().body(Map.of("msg", "Error"));
    }
}
