package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.repositories.CoachRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CoachService {

	@Autowired
	private CoachRepository repository;
	
	@Autowired
	private CountryService countryService;
	
	public List<Coach> findAll() {
		return repository.searchAll();
	}
	
	public Coach findById(Long id) {
		Optional<Coach> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Coach> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<Coach> findByCountry(String text) {
		return repository.findByCountry(text);
	}
	
	public Coach insert(Coach obj) {
		return repository.save(obj);
	}
	
	public Coach update(Long id, Coach obj) {
		try {
			Coach entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Coach entity, Coach obj) {
		entity.setNome(obj.getNome());
		Country country = countryService.findById(obj.getPais().getId());
		entity.setPais(country);
	}
	
	public void delete(Long id) {
		 try {
			 if (!repository.existsById(id)) throw new ResourceNotFoundException(id);
			 repository.deleteById(id);
		 } catch (DataIntegrityViolationException e) {
			 throw new DatabaseException(e.getMessage());
		 }
	}
}