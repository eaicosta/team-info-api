package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.dto.TeamDTO;
import com.devdavicosta.teaminfoapi.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.id, t.nome, t.nome_popular, t.escudo, t.data_fundacao, t.hino, t.pais, "
			+ "NEW com.devdavicosta.teaminfoapi.dto.StateDTO(t.estado.id, t.estado.nome, t.estado.uf), "
			+ "NEW com.devdavicosta.teaminfoapi.dto.StadiumDTO(t.estadio.id, t.estadio.nome, t.estadio.nome_popular), t.tecnico) "
			+ "FROM Team t JOIN FETCH t.tecnico.pais")
	List<TeamDTO> searchAll();
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.id, t.nome, t.nome_popular) FROM Team t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%')) "
			+ "OR LOWER(t.nome_popular) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<TeamDTO> findByName(@Param("nome") String nome);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.id, t.nome, t.nome_popular, "
			+ "NEW com.devdavicosta.teaminfoapi.dto.StateDTO(t.estado.id, t.estado.nome, t.estado.uf)) FROM Team t "
			+ "WHERE LOWER(t.estado.nome) LIKE LOWER(CONCAT('%', :estado, '%')) OR LOWER(t.estado.uf) LIKE LOWER(CONCAT('%', :estado, '%'))")
	List<TeamDTO> findByState(@Param("estado") String estado);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.id, t.nome, t.nome_popular, t.pais) FROM Team t "
			+ "WHERE LOWER(t.pais.nome) LIKE LOWER(CONCAT('%', :pais, '%'))")
	List<TeamDTO> findByCountry(@Param("pais") String pais);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.id, t.nome, t.nome_popular, t.tecnico) FROM Team t JOIN FETCH t.tecnico.pais "
			+ "WHERE LOWER(t.tecnico.nome) LIKE LOWER(CONCAT('%', :tecnico, '%'))")
	List<TeamDTO> findByCoach(@Param("tecnico") String tecnico);
	
	
}