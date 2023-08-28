package cl.bci.api.dto;

public class SuccessDeletedUserDTO {
	
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public SuccessDeletedUserDTO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
}
