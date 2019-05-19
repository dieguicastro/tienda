package com.tienda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**	
 * se crea para excepcion personalizada
 * @author dondi
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -3553724480240514317L;

// esta clase pasa al constructr 
	//padre el menwsaje de la excepcion
	

	public ModeloNotFoundException(String message) {
		super(message);
	} 

	
	
	
}
