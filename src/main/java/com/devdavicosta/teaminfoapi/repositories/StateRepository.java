package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.State;

public interface StateRepository extends JpaRepository<State, Long>{
	
	@Query("SELECT s FROM State s JOIN FETCH s.pais")
	List<State> searchAll();
	
	@Query("SELECT s FROM State s JOIN FETCH s.pais WHERE LOWER(s.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR LOWER(s.uf) "
			+ "LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<State> findByName(@Param("nome") String nome);
	
	@Query("SELECT s FROM State s JOIN FETCH s.pais WHERE LOWER(s.pais.nome) LIKE LOWER(CONCAT('%', :pais, '%'))")
	List<State> findByCountry(@Param("pais") String pais);
}