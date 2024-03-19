package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository repository;
	
	@Autowired
	private CountryService countryService;
	
	public List<State> findAll() {
		return repository.searchAll();
	}
	
	public State findById(Long id) {
		Optional<State> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<State> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<State> findByCountry(String text) {
		return repository.findByCountry(text);
	}
	
	@Transactional
	public State insert(State obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public State update(Long id, State obj) {
		try {
			State entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(State entity, State obj) {
		Country country = countryService.findById(obj.getPais().getId());
		entity.setPais(country);
		entity.setNome(obj.getNome());
		entity.setUf(obj.getUf());
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