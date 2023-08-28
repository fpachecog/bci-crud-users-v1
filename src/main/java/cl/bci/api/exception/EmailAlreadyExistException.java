package cl.bci.api.exception;

public class EmailAlreadyExistException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistException(String errorMessage) {
		super(errorMessage);
	}
	
}
