package cl.bci.api.dto;

public class SuccessUserResponseDTO extends GenericResponseDTO{

	private UserResponseDTO userResponse;
	
	public SuccessUserResponseDTO(UserResponseDTO userResponse) {
		this.userResponse = userResponse;
	}


	public UserResponseDTO getUserResponse() {
		return userResponse;
	}
	public void setUserResponse(UserResponseDTO userResponse) {
		this.userResponse = userResponse;
	}

	@Override
	public String toString() {
		return "SuccessUserResponseDTO [userResponse=" + userResponse + "]";
	}
    
}

