package com.example.webstore.exception;

import com.example.webstore.utils.ApiUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.example.webstore.utils.ApiUtils.error;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleValidationExceptions(MethodArgumentNotValidException errors) {

        Map<String, String> errorMessages = new HashMap<>();
        for(FieldError error:errors.getFieldErrors()){
            String errorField = error.getField(); // 예외 필드
            String errorMessage = error.getDefaultMessage(); // 예외 메세지
            errorMessages.put(errorField,errorMessage);
        }
        return error(errorMessages, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiUtils.ApiResult handleNotFoundProductExceptions(NoSuchElementException error) {
        return error(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleIllegalArgumentException(IllegalArgumentException error) {
        return error(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiUtils.ApiResult handleDuplicateException(DuplicateException error) {
        return error(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException error) {
        return error(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
