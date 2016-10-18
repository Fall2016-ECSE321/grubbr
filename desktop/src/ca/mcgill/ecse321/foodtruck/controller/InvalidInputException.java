package ca.mcgill.ecse321.foodtruck.controller;

/**Error class used to determine invalid inputs
 * 
 * @author erick
 * @version 0.1
 */

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -6369088934697891633L;
	
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}

}
