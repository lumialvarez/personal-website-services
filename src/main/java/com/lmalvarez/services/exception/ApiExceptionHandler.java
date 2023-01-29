package com.lmalvarez.services.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	//Errores en peticion 400
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> validApiRequestException(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<String>();   
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
		ApiException apiException = new ApiException("Schema Validation Failed", details, ex, HttpStatus.BAD_REQUEST); 
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> validApiRequestException(InvalidDataAccessApiUsageException ex) {
		List<String> details = new ArrayList<String>();   
		details.add("Uno o mas campos incompletos, verifique los campos de tipo Objeto");
		details.add(ex.getMessage());
		ApiException apiException = new ApiException("Schema Validation Failed", details, ex, HttpStatus.BAD_REQUEST); 
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
	
	//Datos solicitados no encontrados 404
	@ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> customNotFoundException(CustomNotFoundException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Data Validation Failed", details, ex, HttpStatus.NOT_FOUND); 
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
	
	//Datos con conflicto (ya existe) no encontrados 409
	@ExceptionHandler(CustomConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> customConflictException(CustomConflictException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Data Validation Failed", details, ex, HttpStatus.CONFLICT); 
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }
	
	//Peticion no autizada (por falta token, controlada en JwtEntryPoint) 401
	
	//Login con datos incorrectos 401
	@ExceptionHandler(CustomBadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> customBadCredentialsException(CustomBadCredentialsException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Bad credentials", details, ex, HttpStatus.UNAUTHORIZED); 
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> customInternalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Bad credentials", details, ex, HttpStatus.UNAUTHORIZED); 
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }
	
	//Peticion denegada (Rol sin permisos) 403
	@ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> customAccessDeniedException(AccessDeniedException ex) {
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
		ApiException apiException = new ApiException("Forbidden method", details, ex, HttpStatus.FORBIDDEN); 
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);
    }
	
	//Error en general
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> genericException(Exception ex) {
        ex.printStackTrace();
		List<String> details = new ArrayList<String>();   
        details.add(ex.getMessage());
        details.add(ex.toString());
		ApiException apiException = new ApiException("Internal Error", details, ex, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}