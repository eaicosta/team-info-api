package com.devdavicosta.teaminfoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devdavicosta.teaminfoapi.dto.FootballerDTO;
import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Footballer;
import com.devdavicosta.teaminfoapi.entities.Position;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.devdavicosta.teaminfoapi.repositories.FootballerRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FootballerService {

	@Autowired
	private FootballerRepository repository;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private TeamService teamService;
	
	public List<FootballerDTO> findAll() {
		return repository.searchAll();
	}
	
	public Footballer findById(Long id) {
		Optional<Footballer> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<FootballerDTO> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<FootballerDTO> findByPosition(String text) {
		return repository.findByPosition(text);
	}
	
	public List<FootballerDTO> findByTeam(String text) {
		return repository.findByTeam(text);
	}
	
	public List<FootballerDTO> findByCountry(String text) {
		return repository.findByCountry(text);
	}
	
	@Transactional
	public Footballer insert(Footballer obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public Footballer update(Long id, Footballer obj) {
		try {
			Footballer entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Footballer entity, Footballer obj) {
		entity.setNome(obj.getNome());
		Position position = positionService.findById(obj.getPosicao().getId());
		entity.setPosicao(position);
		Country country = countryService.findById(obj.getPais().getId());
		entity.setPais(country);
		Team time = teamService.findById(obj.getTime().getId());
		entity.setTime(time);
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