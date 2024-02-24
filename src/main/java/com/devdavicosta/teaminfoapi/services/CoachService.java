package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.repositories.CoachRepository;

@Service
public class CoachService {

	@Autowired
	private CoachRepository repository;
	
	public List<Coach> findAll() {
		return repository.findAll();
	}
	
	public Coach findById(Long id) {
		Optional<Coach> obj = repository.findById(id);
		return obj.get();
	}
	
	public Coach insert(Coach obj) {
		return repository.save(obj);
	}
	
	public Coach update(Long id, Coach obj) {
		Coach entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	private void updateData(Coach entity, Coach obj) {
		entity.setNome(obj.getNome());
		entity.setNacionalidade(obj.getNacionalidade());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
