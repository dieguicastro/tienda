package com.tienda.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/***
 * devuleve exepciones del servicio rest
 * @author dondi
 *  sobre esvribe los metodos paraz las exepciones
 *  
 *  se centralizan las escepciones
 */

@ControllerAdvice  // se apoya con esta anotacion -- devuleve exepciones del servicio rest --captura la expecion y la expone en un servicio rest
@RestController // manja exepciones de servicio rest
public class ResponseExcpetionHandler extends ResponseEntityExceptionHandler  {

	
	/**
	 * metodo para centrañizar la expeciones
	 * @param ex la expecion
	 * @param request  el request de lo q entra
	 * @return
	 */
	@ExceptionHandler(ModeloNotFoundException.class) // 
	public final ResponseEntity<Object> manejarModeloExcepciones(ModeloNotFoundException ex, WebRequest request){
		ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)); // false detalle resumido, true mensaje extenso
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		
	}
	
	/**
	 *  exepcion para asumir todas las exepciones
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class) // 
	public final ResponseEntity<Object> manejarTodasExcepciones(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)); // false detalle resumido, true mensaje extenso
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errores ="";
		for(ObjectError e : ex.getBindingResult().getAllErrors()) {
			errores += e.getDefaultMessage() + " /n ";
		}
		ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), errores, request.getDescription(false)); // en el segundo parametro se puede enviar "Validación fallida"
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
