package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.repositories.StadiumRepository;

@Service
public class StadiumService {

	@Autowired
	private StadiumRepository repository;
	
	public List<Stadium> findAll() {
		return repository.findAll();
	}
	
	public Stadium findById(Long id) {
		Optional<Stadium> obj = repository.findById(id);
		return obj.get();
	}
	
	public Stadium insert(Stadium obj) {
		return repository.save(obj);
	}
	
	public Stadium update(Long id, Stadium obj) {
		Stadium entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	private void updateData(Stadium entity, Stadium obj) {
		entity.setNome(obj.getNome());
		entity.setNome_popular(obj.getNome_popular());
		entity.setEstado(obj.getEstado());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
