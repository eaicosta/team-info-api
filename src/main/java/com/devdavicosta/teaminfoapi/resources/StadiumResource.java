package com.devdavicosta.teaminfoapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;
import com.devdavicosta.teaminfoapi.services.StadiumService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value="/stadiums")
public class StadiumResource {
	
	@Autowired
	private StadiumService service;
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping
	public ResponseEntity<List<Stadium>> findAll() {
		List<Stadium> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Stadium> findById(@PathVariable Long id) {
		Stadium obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Stadium> insert(@RequestBody Stadium obj) {
		
		if (obj.getEstado() != null && obj.getEstado().getId() != null) {
			State estado = stateRepository.findById(obj.getEstado().getId()).orElseThrow(() -> new EntityNotFoundException("Estado não encontrado com o id especificado."));
			obj.setEstado(estado);
		} else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Stadium> update(@PathVariable Long id, @RequestBody Stadium obj) {
		
		if (obj.getEstado() != null && obj.getEstado().getId() != null) {
			State estado = stateRepository.findById(obj.getEstado().getId()).orElseThrow(() -> new EntityNotFoundException("Estado não encontrado com o id especificado."));
			obj.setEstado(estado);
		} else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
		
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
