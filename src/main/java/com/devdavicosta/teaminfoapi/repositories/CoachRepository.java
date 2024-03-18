package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long>{
	
	@Query("SELECT c FROM Coach c JOIN FETCH c.pais")
	List<Coach> searchAll();

	@Query("SELECT c FROM Coach c JOIN FETCH c.pais WHERE LOWER (c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Coach> findByName(@Param("nome") String nome);
	
	@Query("SELECT c FROM Coach c JOIN FETCH c.pais WHERE LOWER(c.pais.nome) LIKE LOWER(CONCAT('%', :pais, '%'))")
	List<Coach> findByCountry(@Param("pais") String pais);
}