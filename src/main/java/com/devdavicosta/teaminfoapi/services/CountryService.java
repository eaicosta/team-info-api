package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.repositories.CountryRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repository;
	
	public List<Country> findAll() {
		return repository.findAll();
	}
	
	public Country findById(Long id) {
		Optional<Country> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Country> findByName(String text) {
		return repository.findByName(text);
	}
	
	@Transactional
	public Country insert(Country obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public Country update(Long id, Country obj) {
		try {
			Country entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Country entity, Country obj) {
		entity.setNome(obj.getNome());
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			 if (!repository.existsById(id)) throw new ResourceNotFoundException(id);
			 repository.deleteById(id);
		 } catch (DataIntegrityViolationException e) {
			 throw new DatabaseException("Data integrity constraint violation.");
		 }
	}
}
