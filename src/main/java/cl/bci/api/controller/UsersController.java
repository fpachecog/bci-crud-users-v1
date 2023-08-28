package cl.bci.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.api.dto.GenericResponseDTO;
import cl.bci.api.dto.SuccessListUserResponseDTO;
import cl.bci.api.dto.SuccessUserResponseDTO;
import cl.bci.api.dto.UserResponseDTO;
import cl.bci.api.dto.UsersDTO;
import cl.bci.api.mapper.UserMapper;
import cl.bci.api.model.Users;
import cl.bci.api.service.UserService;

@RestController
@CrossOrigin
public class UsersController {
	
	@Value("${code.success.value}")
	private String successCode;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/getUserByName/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessUserResponseDTO> getUserByName(@PathVariable String name ) throws Exception {
		Users userEntity = userService.getUserByName(name);
		UserResponseDTO userResponseDTO = UserMapper.mapperUserEntityToSuccessResponseDTO(userEntity);
		SuccessUserResponseDTO successResponseDTO = new SuccessUserResponseDTO(userResponseDTO);
		successResponseDTO.setCodigoRespuesta(successCode);
		successResponseDTO.setDescripcionRespuesta("Exito");
		return new ResponseEntity<>(successResponseDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAllUsers", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessListUserResponseDTO> getAllUsers() throws Exception {
		
		List<Users> userList = userService.getAllUsers();
		List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
		
		userList.forEach(entity -> {
			userResponseDTOList.add(UserMapper.mapperUserEntityToSuccessResponseDTO(entity));
		});
		
		SuccessListUserResponseDTO successResponseDTO = new SuccessListUserResponseDTO(userResponseDTOList);
		successResponseDTO.setCodigoRespuesta(successCode);
		successResponseDTO.setDescripcionRespuesta("Exito");
		
		return new ResponseEntity<>(successResponseDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/deleteUserByName/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseDTO> deleteUserByName(@PathVariable String name ) throws Exception {
		userService.deleteUserByName(name);
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO();
		genericResponseDTO.setCodigoRespuesta(successCode);
		genericResponseDTO.setDescripcionRespuesta("Exito");
		return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/saveUser", consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessUserResponseDTO> saveUser(@RequestBody @Valid UsersDTO dto) throws Exception {
		
		Users userEntity = userService.save(dto);
		UserResponseDTO userResponseDTO = UserMapper.mapperUserEntityToSuccessResponseDTO(userEntity);
		SuccessUserResponseDTO successResponseDTO = new SuccessUserResponseDTO(userResponseDTO);
		successResponseDTO.setCodigoRespuesta(successCode);
		successResponseDTO.setDescripcionRespuesta("Exito");
		return new ResponseEntity<>(successResponseDTO, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping(path = "/updateUser", consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessUserResponseDTO> updateUser(@RequestBody @Valid UsersDTO dto) throws Exception {
		
		Users userEntity = userService.update(dto);
		UserResponseDTO userResponseDTO = UserMapper.mapperUserEntityToSuccessResponseDTO(userEntity);
		SuccessUserResponseDTO successResponseDTO = new SuccessUserResponseDTO(userResponseDTO);
		successResponseDTO.setCodigoRespuesta(successCode);
		successResponseDTO.setDescripcionRespuesta("Exito");
		return new ResponseEntity<>(successResponseDTO, HttpStatus.OK);
		
	}
	
}
