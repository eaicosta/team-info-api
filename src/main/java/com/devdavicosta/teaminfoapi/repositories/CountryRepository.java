package com.devdavicosta.teaminfoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devdavicosta.teaminfoapi.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
}
