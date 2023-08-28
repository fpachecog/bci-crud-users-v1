package cl.bci.api.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.bci.api.dto.ErrorResponseDTO;
import cl.bci.api.exception.EmailAlreadyExistException;
import cl.bci.api.exception.PhoneAlreadyExistException;
import cl.bci.api.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	
	@Value("${code.badrequest.value}")
	private String badRequestCode;
	
	@Value("${code.internalerror.value}")
	private String internalErrorCode;
	
	@Value("${code.conflict.value.user.not.found}")
	private String conflictCodeUserNotFound;
	
	@Value("${code.conflict.value.user.email.exist}")
	private String conflictCodeEmailExist;
	
	@Value("${code.conflict.value.user.phone.exist}")
	private String conflictCodePhoneExist;
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseDTO handleInvalidRequest(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		String mensaje = errorMap.toString();
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(mensaje);
		errorResponseDTO.setCodigoRespuesta(badRequestCode);
		errorResponseDTO.setDescripcionRespuesta("Bad Request");
		return errorResponseDTO;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(PhoneAlreadyExistException.class)
	public ErrorResponseDTO handlePhoneAlreadyExistException(PhoneAlreadyExistException ex) {
		
		String mensaje = ex.getMessage();
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(mensaje);
		errorResponseDTO.setCodigoRespuesta(conflictCodePhoneExist);
		errorResponseDTO.setDescripcionRespuesta("El teléfono ya se encuentra registrado para otro usuario");
		return errorResponseDTO;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ErrorResponseDTO handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
		
		String mensaje = ex.getMessage();
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(mensaje);
		errorResponseDTO.setCodigoRespuesta(conflictCodeEmailExist);
		errorResponseDTO.setDescripcionRespuesta("El email ya se encuentra registrado para otro usuario");
		return errorResponseDTO;
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponseDTO handleUserNotFoundException(UserNotFoundException ex) {
		
		String mensaje = ex.getMessage();
		
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(mensaje);
		errorResponseDTO.setCodigoRespuesta(conflictCodeUserNotFound);
		errorResponseDTO.setDescripcionRespuesta("No se encuentran registros");
		return errorResponseDTO;
	}
	
	
	
	
	
}
