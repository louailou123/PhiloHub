package App.PhiloHub.Exeptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // Validation errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
      assert ex.getBindingResult() != null;
      ex.getBindingResult().getFieldErrors()
            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  // Custom exceptions
  @ExceptionHandler(QuoteException.class)
  public ResponseEntity<?> handleQuoteException(QuoteException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", ex.getMessage()));
  }

  // Generic exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Something went wrong"));
  }
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex)
  {
    return ResponseEntity.status(401).body(Map.of("error",ex.getMessage()));
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleDataIntegrityViolationExeption (DataIntegrityViolationException ex)
  {
    return ResponseEntity.status(400).body(Map.of("error", "Duplicted value"));
  }
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<?> handleNoHandlerFoundException ()
  {
    return ResponseEntity.status(404).body(Map.of("error","method not found"));
  }
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> handleMethodNotSupportedException (HttpRequestMethodNotSupportedException ex)
  {
    return ResponseEntity.status(404).body(Map.of("error",ex.getMessage()));
  }
}
