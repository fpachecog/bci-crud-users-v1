package cl.bci.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class PhoneDTO {
	
	@NotNull
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message="El campo number no es valido")
    private String number;
	
	@NotNull
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message="El campo citycode no es valido")
	@Size(max = 10, message = "Em campo citycode no puede superar los 10 caracteres")
    private String citycode;
    
	@NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{2}$", message="El campo contrycode no es valido")
    private String contrycode;

	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	@Override
	public String toString() {
		return "FinalPhoneDTO [number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode + "]";
	}
	

}
