package com.example.webstore.exception;

import com.example.webstore.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.example.webstore.utils.ApiUtils.error;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler //(MethodArgumentNotValidException.class) 매개변수랑 같으니까 빼도됨
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400에러
    public ApiUtils.ApiResult handleValidationExceptions(MethodArgumentNotValidException errors) {

        Map<String, String> errorMessages = new HashMap<>();
        for(FieldError error:errors.getFieldErrors()){
            String errorField = error.getField(); // 예외 필드
            String errorMessage = error.getDefaultMessage(); // 예외 메세지
            errorMessages.put(errorField,errorMessage);
        }
        //log.info("errorMessage = {}",errorMessages.toString());
        return error(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
