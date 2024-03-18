package com.devdavicosta.teaminfoapi.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.devdavicosta.teaminfoapi.dto.RivalDTO;
import com.devdavicosta.teaminfoapi.dto.TeamDTO;
import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.devdavicosta.teaminfoapi.repositories.TeamRepository;
import com.devdavicosta.teaminfoapi.services.exceptions.DatabaseException;
import com.devdavicosta.teaminfoapi.services.exceptions.ResourceNotFoundException;
import com.devdavicosta.teaminfoapi.services.exceptions.SameIdException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {

	@Autowired
	private TeamRepository repository;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private StadiumService stadiumService;
	
	@Autowired
	private CoachService coachService;
	
	public List<TeamDTO> findAll() {
		return repository.searchAll();
	}
	
	public Team findById(Long id) {
		Optional<Team> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<TeamDTO> findByName(String text) {
		return repository.findByName(text);
	}
	
	public List<TeamDTO> findByState(String text) {
		return repository.findByState(text);
	}
	
	public List<TeamDTO> findByCountry(String text) {
		return repository.findByCountry(text);
	}
	
	public List<TeamDTO> findByCoach(String text) {
		return repository.findByCoach(text);
	}
	
	public RivalDTO getRivals(Long id) {
		Optional<Team> optEntity = repository.findById(id);
		
		if (optEntity.isPresent()) {
			Team entity = optEntity.get();
			return new RivalDTO(entity);
		} else {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public RivalDTO insertRivals(Long idTime, Long idRival) {
		Team time = repository.findById(idTime).orElseThrow(() -> new ResourceNotFoundException(idTime));
		Team rival = repository.findById(idRival).orElseThrow(() -> new ResourceNotFoundException(idRival));
		
		if (rival.equals(time)) {
		    throw new SameIdException();
		}
		
		time.getRivais().add(rival);
		repository.save(time);
		
		return new RivalDTO(time);
	}
	
	public Team insert(Team obj) {
		return repository.save(obj);
	}
	
	public Team update(Long id, Team obj) {
		try {
			Team entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Team entity, Team obj) {
		entity.setNome(obj.getNome());
		entity.setNome_popular(obj.getNome_popular());
		entity.setEscudo(obj.getEscudo());
		Country country = countryService.findById(obj.getPais().getId());
		entity.setPais(country);
		entity.setData_fundacao(obj.getData_fundacao());
		entity.setHino(obj.getHino());
		State state = stateService.findById(obj.getEstado().getId());
		entity.setEstado(state);
		Stadium stadium = stadiumService.findById(obj.getEstadio().getId());
		entity.setEstadio(stadium);
		Coach coach = coachService.findById(obj.getTecnico().getId());
		entity.setTecnico(coach);
	}
	
	public Team updatePatch(Long id, Map<String, Object> fields) {
		try {
			Optional<Team> optEntity = repository.findById(id);
			Team entity = optEntity.get();
			merge(fields, entity);
			return repository.save(entity);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void merge(Map<String, Object> fields, Team entity) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		Team entityConvert = objectMapper.convertValue(fields, Team.class);
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Team.class, key);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, entityConvert);
			
			ReflectionUtils.setField(field, entity, newValue);
		});
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