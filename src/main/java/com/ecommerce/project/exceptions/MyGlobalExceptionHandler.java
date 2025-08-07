package com.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Bu sınıf, Spring Boot uygulamasında global (küresel) exception handler olarak görev yapar.
 * <p>
 * @RestControllerAdvice anotasyonu sayesinde, tüm @RestController sınıflarında meydana gelen
 * belirli istisnalar merkezi olarak yakalanır ve kullanıcıya özelleştirilmiş, anlaşılır bir
 * HTTP yanıtı döndürülür.
 * <p>
 * Özellikle form validasyonu (@Valid) sırasında oluşan MethodArgumentNotValidException türündeki
 * hataları yakalayarak, hangi alanda ne tür bir validasyon hatası olduğunu JSON formatında sunar.
 * Böylece API kullanıcılarına daha kullanıcı dostu geri dönüşler sağlanmış olur.
 *
 * Örnek çıktı:
 * {
 *   "email": "Email boş olamaz",
 *   "password": "Şifre en az 6 karakter olmalı"
 * }
 *
 * @author seval
 */

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Bu metot, sadece validasyon hatası (@Valid, @NotBlank, @Size, vb.) olduğunda çalışır.
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){ //Spring'in form veya DTO validasyonu sırasında fırlattığı bir exception türüdür.
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e){

    }
}

