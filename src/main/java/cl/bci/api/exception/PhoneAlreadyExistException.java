package cl.bci.api.exception;

public class PhoneAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public PhoneAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
	
}
