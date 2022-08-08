package com.jogo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CentralDeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){

		String detail = ex.getMessage();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(EntidadeNaoProcessavelException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoProcessavelException ex, WebRequest request){

		String detail = ex.getMessage();
		return handleExceptionInternal(ex, detail, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

}
