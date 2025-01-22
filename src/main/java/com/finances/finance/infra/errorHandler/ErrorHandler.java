package com.finances.finance.infra.errorHandler;

import com.finances.finance.errors.ModalityCreationError;
import com.finances.finance.errors.PaymentMethodNotFindError;
import com.finances.finance.errors.UserCreationError;
import com.finances.finance.errors.UserNotFindError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFindError.class)
    private ResponseEntity<ErrorEntity> userNotFindHandler(UserNotFindError error) {

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setError(error.getClass().getSimpleName());
        errorEntity.setMessage(error.getMessage());
        errorEntity.setStatus(HttpStatus.NOT_FOUND.value());
        errorEntity.setTimestamp(new Date());

        return ResponseEntity.status(errorEntity.getStatus()).body(errorEntity);
    }

    @ExceptionHandler(PaymentMethodNotFindError.class)
    private ResponseEntity<ErrorEntity> userNotFindHandler(PaymentMethodNotFindError error) {

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setError(error.getClass().getSimpleName());
        errorEntity.setMessage(error.getMessage());
        errorEntity.setStatus(HttpStatus.NOT_FOUND.value());
        errorEntity.setTimestamp(new Date());

        return ResponseEntity.status(errorEntity.getStatus()).body(errorEntity);
    }

    @ExceptionHandler(ModalityCreationError.class)
    private ResponseEntity<ErrorEntity> modalityCreationError(ModalityCreationError error) {

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setError(error.getClass().getSimpleName());
        errorEntity.setMessage(error.getMessage());
        errorEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        errorEntity.setTimestamp(new Date());

        return ResponseEntity.status(errorEntity.getStatus()).body(errorEntity);
    }

    @ExceptionHandler(UserCreationError.class)
    private ResponseEntity<ErrorEntity> userCreationError(UserCreationError error) {

        ErrorEntity errorEntity = new ErrorEntity();

        errorEntity.setError(error.getClass().getSimpleName());
        errorEntity.setMessage(error.getMessage());
        errorEntity.setStatus(HttpStatus.BAD_REQUEST.value());
        errorEntity.setTimestamp(new Date());

        return ResponseEntity.status(errorEntity.getStatus()).body(errorEntity);
    }

}
