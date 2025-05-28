package at.fhtw.mse.awt.twentyone.config;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAny(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof EntityNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        }

        log.error("API-Fehler: {}", ex.getMessage(), ex);

        Map<String, String> body = new HashMap<>();
        // wir verpacken hier den Serverfehler als Message und zeigen diese im Frontend
        // im best practice wuerde man hier sicherlich nicht die Fehler 1:1 an den Client liefern, sondern in
        // z.B. Clientfehler 4xx und Serverfehler 5xx unterteilen, aber hier im kleinen Projekt mal so.
        body.put("message", ex.getMessage() != null ? ex.getMessage() : "Unbekannter Fehler");

        return ResponseEntity.status(status).body(body);
    }
}