package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repository;
	
	public List<State> findAll() {
		return repository.findAll();
	}
	
	public State findById(Long id) {
		Optional<State> obj = repository.findById(id);
		return obj.get();
	}
	
	public State insert(State obj) {
		return repository.save(obj);
	}
	
	public State update(Long id, State obj) {
		State entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	private void updateData(State entity, State obj) {
		entity.setNome(obj.getNome());
		entity.setUf(obj.getUf());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}