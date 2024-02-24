package com.devdavicosta.teaminfoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devdavicosta.teaminfoapi.entities.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long>{
}
