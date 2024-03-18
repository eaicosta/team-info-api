package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.dto.TournamentDTO;
import com.devdavicosta.teaminfoapi.entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TournamentDTO(t.id, t.nome, t.ano_edicao, "
			+ "NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.time_campeao.id, t.time_campeao.nome, t.time_campeao.nome_popular, "
			+ "t.time_campeao.pais)) FROM Tournament t")
	List<TournamentDTO> searchAll();
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TournamentDTO(t.id, t.nome, t.ano_edicao) FROM Tournament t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<TournamentDTO> findByName(@Param("nome") String nome);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TournamentDTO(t.id, t.nome, t.ano_edicao) FROM Tournament t WHERE t.ano_edicao LIKE CONCAT('%', :ano, '%')")
	List<TournamentDTO> findByYear(@Param("ano") String ano);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.TournamentDTO(t.id, t.nome, t.ano_edicao, "
			+ "NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(t.time_campeao.id, t.time_campeao.nome, t.time_campeao.nome_popular, "
			+ "t.time_campeao.pais)) FROM Tournament t WHERE LOWER(t.time_campeao.nome) LIKE LOWER(CONCAT('%', :time, '%')) "
			+ "OR LOWER(t.time_campeao.nome_popular) LIKE LOWER(CONCAT('%', :time, '%'))")
	List<TournamentDTO> findByChampion(@Param("time") String time);
	
}