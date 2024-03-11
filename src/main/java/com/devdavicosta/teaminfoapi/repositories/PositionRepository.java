package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	
	@Query("SELECT p FROM Position p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR LOWER(p.abreviacao) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Position> findByName(@Param("nome") String nome);
}
