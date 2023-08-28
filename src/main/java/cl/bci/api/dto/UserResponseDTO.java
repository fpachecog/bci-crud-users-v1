package cl.bci.api.dto;

import java.util.Date;
import java.util.UUID;

public class UserResponseDTO {
	
	private UUID id;
	private String name;
	private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "SuccessResponseDTO [id=" + id + ", name=" + name + ", created=" + created + ", modified=" + modified
				+ ", lastLogin=" + lastLogin + ", token=" + token + "]";
	}
	
}
