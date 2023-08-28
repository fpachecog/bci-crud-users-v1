package cl.bci.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.api.config.JwtTokenUtil;
import cl.bci.api.dto.JwtRequestDTO;
import cl.bci.api.dto.JwtResponseDTO;
import cl.bci.api.dto.UsersDTO;
import cl.bci.api.model.Users;
import cl.bci.api.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		Users userEntity = userService.getUserByName(authenticationRequest.getUsername());
		userEntity.setToken(token);
		userEntity.setLastLogin(new Date());
		
		userService.updateUser(userEntity);

		return ResponseEntity.ok(new JwtResponseDTO(token));
	}
	
	public String generateAuthenticationToken(String name, String password) throws Exception {
		authenticate(name, password);

		final UserDetails userDetails = userService.loadUserByUsername(name);

		return jwtTokenUtil.generateToken(userDetails);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UsersDTO user) throws Exception {
		return ResponseEntity.ok(userService.save(user));
	}
	
	
	

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}