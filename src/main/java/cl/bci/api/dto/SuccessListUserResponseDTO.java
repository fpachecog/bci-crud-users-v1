package cl.bci.api.dto;

import java.util.List;

public class SuccessListUserResponseDTO extends GenericResponseDTO{

	private List<UserResponseDTO> userResponseList;
	
	public SuccessListUserResponseDTO(List<UserResponseDTO> userResponseList) {
		super();
		this.userResponseList = userResponseList;
	}

	public List<UserResponseDTO> getUserResponseList() {
		return userResponseList;
	}
	public void setUserResponseList(List<UserResponseDTO> userResponseList) {
		this.userResponseList = userResponseList;
	}

	@Override
	public String toString() {
		return "SuccessListUserResponseDTO [userResponseList=" + userResponseList + "]";
	}
    
}

