package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	
	@Query("SELECT c FROM Country c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Country> findByName(@Param("nome") String nome);
}
