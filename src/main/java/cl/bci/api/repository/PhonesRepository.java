package cl.bci.api.repository;

import org.springframework.stereotype.Repository;

import cl.bci.api.model.Phones;
import cl.bci.api.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, String>{
	Phones findOneByNumber(String number);
	Phones findOneByNumberAndUserId(String number, Users userId);
	Phones findOneByNumberAndUserIdNot(String number, Users userId);
}
