package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdavicosta.teaminfoapi.dto.TournamentDTO;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.devdavicosta.teaminfoapi.entities.Tournament;
import com.devdavicosta.teaminfoapi.repositories.TournamentRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TournamentService {

	@Autowired
	private TournamentRepository repository;
	
	@Autowired
	private TeamService teamService;
	
	public List<TournamentDTO> findAll() {
		return repository.searchAll();
	}
	
	public Tournament findById(Long id) {
		Optional<Tournament> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	public List<TournamentDTO> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<TournamentDTO> findByYear(String text) {
		return repository.findByYear(text);
	}
	
	public List<TournamentDTO> findByChampion(String text) {
		return repository.findByChampion(text);
	}
	
	@Transactional
	public Tournament insert(Tournament obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public Tournament update(Long id, Tournament obj) {
		try {
			Tournament entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Tournament entity, Tournament obj) {
		entity.setNome(obj.getNome());
		entity.setAno_edicao(obj.getAno_edicao());
		Team team = teamService.findById(obj.getTime_campeao().getId());
		entity.setTime_campeao(team);
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