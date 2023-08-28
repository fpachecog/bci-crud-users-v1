package cl.bci.api.dto;

public class GenericResponseDTO {

	private String codigoRespuesta;
	private String descripcionRespuesta;
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}
	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}
	@Override
	public String toString() {
		return "GenericResponse [codigoRespuesta=" + codigoRespuesta + ", descripcionRespuesta=" + descripcionRespuesta
				+ "]";
	}
	
}
