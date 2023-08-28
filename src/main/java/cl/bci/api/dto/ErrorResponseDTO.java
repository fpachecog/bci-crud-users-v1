package cl.bci.api.dto;

public class ErrorResponseDTO extends GenericResponseDTO{

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public ErrorResponseDTO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	
}
