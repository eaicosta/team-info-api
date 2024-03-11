package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devdavicosta.teaminfoapi.entities.Position;
import com.devdavicosta.teaminfoapi.repositories.PositionRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PositionService {

	@Autowired
	private PositionRepository repository;
	
	public List<Position> findAll() {
		return repository.findAll();
	}
	
	public Position findById(Long id) {
		Optional<Position> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Position> findByName(String text) {
		return repository.findByName(text);
	}
	
	public Position insert(Position obj) {
		return repository.save(obj);
	}
	
	public Position update(Long id, Position obj) {
		try {
			Position entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Position entity, Position obj) {
		entity.setNome(obj.getNome());
		entity.setAbreviacao(obj.getAbreviacao());
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