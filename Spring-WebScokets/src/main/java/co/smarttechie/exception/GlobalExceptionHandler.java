package co.smarttechie.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import co.smarttechie.model.WebSocketMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @MessageExceptionHandler(ResourceNotFoundException.class)
    @SendToUser("/queue/errors")
    public WebSocketMessage handleWebSocketResourceNotFoundException(ResourceNotFoundException ex) {
        return new WebSocketMessage("ERROR", new ErrorResponse(ex.getMessage()));
    }

    record ErrorResponse(String message) {}
} 