package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.StadiumRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StadiumService {

	@Autowired
	private StadiumRepository repository;
	
	@Autowired
	private StateService stateService;
	
	public List<Stadium> findAll() {
		return repository.searchAll();
	}
	
	public Stadium findById(Long id) {
		Optional<Stadium> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Stadium> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<Stadium> findByState(String text) {
		return repository.findByState(text);
	}
	
	@Transactional
	public Stadium insert(Stadium obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public Stadium update(Long id, Stadium obj) {
		try {
			Stadium entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Stadium entity, Stadium obj) {
		entity.setNome(obj.getNome());
		entity.setNome_popular(obj.getNome_popular());
		State state = stateService.findById(obj.getEstado().getId());
		entity.setEstado(state);
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