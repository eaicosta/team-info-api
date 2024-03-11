package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Footballer;

public interface FootballerRepository extends JpaRepository<Footballer, Long>{
	
	@Query("SELECT f FROM Footballer f WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Footballer> findByName(@Param("nome") String nome);
	
	@Query("SELECT f FROM Footballer f WHERE LOWER(f.posicao.nome) LIKE LOWER(CONCAT('%', :posicao, '%')) OR "
			+ "LOWER(f.posicao.abreviacao) LIKE LOWER(CONCAT('%', :posicao, '%'))")
	List<Footballer> findByPosition(@Param("posicao") String posicao);
	
	@Query("SELECT f FROM Footballer f WHERE LOWER(f.time.nome) LIKE LOWER(CONCAT('%', :time, '%')) OR "
			+ "LOWER(f.time.nome_popular) LIKE LOWER(CONCAT('%', :time, '%'))")
	List<Footballer> findByTeam(@Param("time") String time);
	
	@Query("SELECT f FROM Footballer f WHERE LOWER(f.pais.nome) LIKE LOWER(CONCAT('%', :pais, '%'))")
	List<Footballer> findByCountry(@Param("pais") String pais);
}
