package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
	@Query("SELECT t FROM Team t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR LOWER(t.nome_popular) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Team> findByTeamName(@Param("nome") String nome);
	
	@Query("SELECT t FROM Team t WHERE LOWER(t.estado.nome) LIKE LOWER(CONCAT('%', :estado, '%')) OR LOWER(t.estado.uf) LIKE LOWER(CONCAT('%', :estado, '%'))")
	List<Team> findByState(@Param("estado") String estado);
	
	@Query("SELECT t from Team t WHERE LOWER(t.pais.nome) LIKE LOWER(CONCAT('%', :pais,'%'))")
	List<Team> findByCountry(@Param("pais") String pais);
}