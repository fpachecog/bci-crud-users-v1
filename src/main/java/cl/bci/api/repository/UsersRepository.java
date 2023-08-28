package cl.bci.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bci.api.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{
	List<Users> findAll();
	Users findOneByUserId(UUID userId);
	Users findOneByName(String name);
	Users findOneByEmail(String email);
	Users findOneByEmailAndUserIdNot(String email, UUID userId);
	void deleteByName(String name);
	void deleteByUserId(UUID userId);
	
}
