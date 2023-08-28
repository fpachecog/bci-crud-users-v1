package cl.bci.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.api.dto.PhoneDTO;
import cl.bci.api.dto.UsersDTO;
import cl.bci.api.exception.EmailAlreadyExistException;
import cl.bci.api.exception.PhoneAlreadyExistException;
import cl.bci.api.exception.UserNotFoundException;
import cl.bci.api.model.Phones;
import cl.bci.api.model.Users;
import cl.bci.api.repository.PhonesRepository;
import cl.bci.api.repository.UsersRepository;

@Service
public class UserService implements UserDetailsService {
	
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private PhonesRepository phonesRepository;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Users user = getUserByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with name: " + name);
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				new ArrayList<>());
	}
	
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Users getUserByName(String name) {
		Users user = userRepository.findOneByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with name: " + name);
		}
		return user;
	}
	
	public Users getUserByUUID(UUID uuid) {
		Users user = userRepository.findOneByUserId(uuid);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with UUID: " + uuid);
		}
		return user;
	}
	
	public Boolean updateUser(Users user) {
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		try {
			userRepository.save(user);
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
	
	@Transactional(rollbackFor = {EmailAlreadyExistException.class, PhoneAlreadyExistException.class})
	public Users update(UsersDTO dto) throws Exception {
		Users userEntity = userRepository.findOneByName(dto.getName());
		if(userEntity == null) {
			throw new UserNotFoundException("No se encuentra el usuario");
		}
			
		
		if(userRepository.findOneByEmailAndUserIdNot(dto.getEmail(), userEntity.getId()) != null) {
			throw new EmailAlreadyExistException("El email "+dto.getEmail()+ " ya se encuentra registrado");
		}
		else {
			
			userEntity.setName(dto.getName());
			userEntity.setEmail(dto.getEmail());
			userEntity.setPassword(bcryptEncoder.encode(dto.getPassword()));
			userEntity.setModified(new Date());
			userEntity.setIsActive(Boolean.TRUE);
			// guardo para actualizar cambios de usuario
			userRepository.save(userEntity);
			
			// vuelvo a generar un token por si cambió el valor del password
			String token = tokenService.generateAuthenticationToken(dto.getName(), dto.getPassword());
			userEntity.setToken(token);
			userEntity.setModified(new Date());
			// actualizo el nuevo valor del token en DB
			userRepository.save(userEntity);
			
			if(dto.getPhones() != null && !dto.getPhones().isEmpty()) {
				for(PhoneDTO phoneDTO : dto.getPhones()) {
					
					Phones phoneDB = phonesRepository.findOneByNumberAndUserId(phoneDTO.getNumber(), userEntity);
					if(phoneDB != null) {
						phoneDB.setCityCode(phoneDTO.getCitycode());
						phoneDB.setContryCode(phoneDTO.getContrycode());
						phonesRepository.save(phoneDB);
					}
					else {
						
						if(phonesRepository.findOneByNumberAndUserIdNot(phoneDTO.getNumber(), userEntity) != null) {
							//Existe otro usuario con el mismo numero
							throw new PhoneAlreadyExistException("El numero "+phoneDTO.getNumber()+ " ya se encuentra registrado");
						}
						
						Phones phoneEntity = new Phones(phoneDTO.getNumber(), phoneDTO.getCitycode(), phoneDTO.getContrycode(), userEntity);
						phonesRepository.save(phoneEntity);
						
					}
					
				}
			}
			
			return userEntity;
			
		}
		
	}
	
	public void deleteUserByName(String name) throws UserNotFoundException {
		Users entity = userRepository.findOneByName(name);
		if(entity != null) {
			userRepository.delete(entity);
		}
		else {
			throw new UserNotFoundException("No se encuentra el usuario");
		}
	}
	
	public void deleteUserByUserId(String userId) throws UserNotFoundException {
		UUID userUUID = UUID.fromString(userId);
		Users entity = userRepository.findOneByUserId(userUUID);
		if(entity != null) {
			userRepository.delete(entity);
		}
		else {
			throw new UserNotFoundException("No se encuentra el usuario");
		}
	}
	
	
	@Transactional(rollbackFor = {EmailAlreadyExistException.class, PhoneAlreadyExistException.class})
	public Users save(UsersDTO dto) throws Exception {
		
		Users entity = new Users();
		
		if(userRepository.findOneByEmail(dto.getEmail()) != null) {
			throw new PhoneAlreadyExistException("El email "+dto.getEmail()+ " ya se encuentra registrado");
		}
		else {
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setPassword(bcryptEncoder.encode(dto.getPassword()));
			
			
			Date now = new Date();
			
			entity.setCreated(now);
			entity.setModified(now);
			entity.setLastLogin(now);
			entity.setIsActive(Boolean.TRUE);
			entity.setToken("TOKEN TEMPORAL");
			userRepository.save(entity);
			String token = tokenService.generateAuthenticationToken(dto.getName(), dto.getPassword());
			entity.setToken(token);
			entity.setModified(new Date());
			userRepository.save(entity);
			
			if(dto.getPhones() != null && !dto.getPhones().isEmpty()) {
				for(PhoneDTO phoneDTO : dto.getPhones()) {
					Phones phoneEntity = new Phones(phoneDTO.getNumber(), phoneDTO.getCitycode(), phoneDTO.getContrycode(), entity);
					
					if(phonesRepository.findOneByNumber(phoneDTO.getNumber()) != null) {
						throw new PhoneAlreadyExistException("El número "+phoneDTO.getNumber()+ " ya se encuentra registrado");
					}
					
					phonesRepository.save(phoneEntity);
				}
			}
			
			return entity;
			
		}
		
	}
	
}