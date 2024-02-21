package com.devdavicosta.teaminfoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devdavicosta.teaminfoapi.entities.State;

public interface StateRepository extends JpaRepository<State, Long>{
}