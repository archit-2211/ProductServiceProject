package com.architproject.productservice.ControllerAdvice;


import com.architproject.productservice.DTOs.ErrorDTO;
import com.architproject.productservice.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> productNotFoundException(ProductNotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        ResponseEntity<ErrorDTO> entity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST) ;

        return entity;

    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorDTO> runtimeException(RuntimeException e) {
//        ErrorDTO errorDTO = new ErrorDTO();
//        errorDTO.setError("Something Went Wrong Unexpected Error");
//        ResponseEntity<ErrorDTO> entity = new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR) ;
//
//        return entity;
//    }
}
