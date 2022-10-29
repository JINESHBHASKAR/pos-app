package com.pos.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.pos.app.constants.AppConstants;
import com.pos.app.exception.BusinessException;
import com.pos.app.vo.ErrorResponse;
import com.pos.app.vo.StatusResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody StatusResponse handleCommonException(final Exception exception) {
		log.info("inside handleCommonException CustomExceptionHandler");
		//ErrorResponse error = new ErrorResponse();
		StatusResponse error = new StatusResponse();
		error.setMessage(exception.getMessage());
		error.setStatus(AppConstants.STATUS_FAILED);
		error.setData("");		
		return error;
	}
	
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody StatusResponse handleBusinessException(final BusinessException exception) {
		log.info("inside handleBusinessException CustomExceptionHandler");
		//ErrorResponse error = new ErrorResponse();
		StatusResponse error = new StatusResponse();
		error.setMessage(exception.getMessage());
		error.setStatus(AppConstants.STATUS_FAILED);
		error.setData("");		
		return error;
	} 
	
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody StatusResponse unAuthorizedException(final BusinessException exception) {
		log.info("inside unAuthorizedException CustomExceptionHandler");
		//ErrorResponse error = new ErrorResponse();
		StatusResponse error = new StatusResponse();
		error.setMessage(exception.getMessage());
		error.setStatus(AppConstants.STATUS_FAILED);
		error.setData("");		
		return error;
	} 
	
	
}
