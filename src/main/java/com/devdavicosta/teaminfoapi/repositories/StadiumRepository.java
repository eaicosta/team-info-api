package com.devdavicosta.teaminfoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devdavicosta.teaminfoapi.entities.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long>{
	@Query("SELECT s FROM Stadium s WHERE LOWER(s.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR LOWER(s.nome_popular) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Stadium> findByStadiumName(@Param("nome") String nome);
	
	@Query("SELECT s FROM Stadium s WHERE LOWER(s.estado.nome) LIKE LOWER(CONCAT('%', :estado, '%')) OR LOWER(s.estado.uf) LIKE LOWER(CONCAT('%', :estado, '%'))")
	List<Stadium> findByState(@Param("estado") String estado);
}
