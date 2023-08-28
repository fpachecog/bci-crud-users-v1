package cl.bci.api.mapper;

import cl.bci.api.dto.UserResponseDTO;
import cl.bci.api.model.Users;

public class UserMapper {

	private UserMapper() {
		
	}
	
	public static UserResponseDTO mapperUserEntityToSuccessResponseDTO(Users userEntity) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(userEntity.getId());
		userResponseDTO.setModified(userEntity.getModified());
		userResponseDTO.setCreated(userEntity.getCreated());
		userResponseDTO.setLastLogin(userEntity.getLastLogin());
		userResponseDTO.setName(userEntity.getName());
		userResponseDTO.setToken(userEntity.getToken());
		
		return userResponseDTO;
		
	}
	
	
}
