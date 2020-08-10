package com.demo.crud.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.demo.crud.entities.Phone;
@CrossOrigin(origins="http://localhost:8081") 
@Repository
public interface PhoneRespositoryInterface extends JpaRepository<Phone, Long> {
	
}
	