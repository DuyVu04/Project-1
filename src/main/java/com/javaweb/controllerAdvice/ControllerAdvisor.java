package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponeDTO;

import customexception.FieldRequiredException;
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(
			ArithmeticException ex,  WebRequest request) {
		ErrorResponeDTO errorResponeDTO = new ErrorResponeDTO();
		List<String> details = new ArrayList<>();
		errorResponeDTO.setError(ex.getMessage());
		details.add("So nguyen khong chia dc cho 0!");
		errorResponeDTO.setDetail(details);

        return new ResponseEntity<>(errorResponeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleFieldRequiredException(
			FieldRequiredException ex,  WebRequest request) {
		ErrorResponeDTO errorResponeDTO = new ErrorResponeDTO();
		List<String> details = new ArrayList<>();
		errorResponeDTO.setError(ex.getMessage());
		details.add("Thiếu 1 trong 2 rồi kìa");
		errorResponeDTO.setDetail(details);
        return new ResponseEntity<>(errorResponeDTO, HttpStatus.BAD_GATEWAY);
    }
}

