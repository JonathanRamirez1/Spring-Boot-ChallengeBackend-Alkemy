package com.api.ChallengeBackend.web.exceptions;

import com.api.ChallengeBackend.web.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@ControllerAdvice
@RestController
public class customExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleConflict()
            throws IOException {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("No tienes permiso para realizar esta acción"));
    }
}
