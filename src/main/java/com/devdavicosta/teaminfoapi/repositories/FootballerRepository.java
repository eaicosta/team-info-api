package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.dto.FootballerDTO;
import com.devdavicosta.teaminfoapi.entities.Footballer;

public interface FootballerRepository extends JpaRepository<Footballer, Long>{
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.FootballerDTO(f.id, f.nome, f.posicao, f.pais, "
			+ "NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(f.time.id, f.time.nome_popular)) "
			+ "FROM Footballer f")
	List<FootballerDTO> searchAll();
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.FootballerDTO(f.id, f.nome) FROM Footballer f "
			+ "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<FootballerDTO> findByName(@Param("nome") String nome);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.FootballerDTO(f.id, f.nome, f.posicao) FROM Footballer f "
			+ "WHERE LOWER(f.posicao.nome) LIKE LOWER(CONCAT('%', :posicao, '%')) OR LOWER(f.posicao.abreviacao) "
			+ "LIKE LOWER(CONCAT('%', :posicao, '%'))")
	List<FootballerDTO> findByPosition(@Param("posicao") String posicao);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.FootballerDTO(f.id, f.nome, " +
		       "NEW com.devdavicosta.teaminfoapi.dto.TeamDTO(f.time.id, f.time.nome_popular)) " +
		       "FROM Footballer f " +
		       "WHERE LOWER(f.time.nome) LIKE LOWER(CONCAT('%', :time, '%')) OR " +
		       "LOWER(f.time.nome_popular) LIKE LOWER(CONCAT('%', :time, '%'))")
	List<FootballerDTO> findByTeam(@Param("time") String time);
	
	@Query("SELECT NEW com.devdavicosta.teaminfoapi.dto.FootballerDTO(f.id, f.nome, f.pais) FROM Footballer f "
			+ "WHERE LOWER(f.pais.nome) LIKE LOWER(CONCAT('%', :pais, '%'))")
	List<FootballerDTO> findByCountry(@Param("pais") String pais);
}
