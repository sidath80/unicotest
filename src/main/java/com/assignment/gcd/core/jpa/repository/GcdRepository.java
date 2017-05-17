package com.assignment.gcd.core.jpa.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.assignment.gcd.core.jpa.domain.IntNumber;

public interface GcdRepository extends CrudRepository<IntNumber, Long>{
	
	List<IntNumber> findAll();
}
