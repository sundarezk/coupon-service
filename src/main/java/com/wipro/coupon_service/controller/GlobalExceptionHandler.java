package com.wipro.coupon_service.controller;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wipro.coupon_service.exception.CouponException;
import com.wipro.coupon_service.reponse.ErrorResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(CouponException.class)
public ResponseEntity<ErrorResponse> handleCouponException(CouponException e)

{
	ErrorResponse errorResponse= new ErrorResponse();
	errorResponse.setMessage(e.toString());
	errorResponse.setStatus(HttpStatus.NOT_FOUND);
	return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	
	
}
}