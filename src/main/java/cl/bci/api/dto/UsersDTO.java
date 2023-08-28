package cl.bci.api.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersDTO {
	
	private UUID id;
	
	@NotNull(message = "Em campo name no puede ser nulo")
	@NotEmpty(message = "Em campo name no puede estar vacío")
	private String name;
	
	@NotNull(message = "Em campo email no puede ser nulo")
	@NotEmpty(message = "Em campo email no puede estar vacío")
	@Size(max = 100, message = "Em campo email no puede superar los 100 caracteres")
	@Email
	private String email;
	
	@NotNull(message = "Em campo password no puede ser nulo")
	@NotEmpty(message = "Em campo password no puede estar vacío")
	@Size(max = 100, message = "Em campo password no puede superar los 100 caracteres")
	private String password;
	
	private String token;
	
	@Valid
	@NotNull(message = "Em campo phones no puede ser nulo")
	@NotEmpty(message = "Em campo phones no puede estar vacío")
	private List<PhoneDTO> phones;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "FinalUserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", token="
				+ token + ", phones=" + phones + "]";
	}
	
	
}

