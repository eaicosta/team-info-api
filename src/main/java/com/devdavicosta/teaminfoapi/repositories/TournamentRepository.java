package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
	@Query("SELECT t FROM Tournament t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Tournament> findByTournamentName(@Param("nome") String nome);
	
	@Query("SELECT t FROM Tournament t WHERE t.ano_edicao LIKE CONCAT('%', :ano, '%')")
	List<Tournament> findByYear(@Param("ano") String ano);
	
	@Query("SELECT t from Tournament t WHERE LOWER(t.time_campeao.nome) LIKE LOWER(CONCAT('%', :time, '%')) OR LOWER(t.time_campeao.nome_popular) LIKE LOWER(CONCAT('%', :time, '%'))")
	List<Tournament> findByChampion(@Param("time") String time);
	
}