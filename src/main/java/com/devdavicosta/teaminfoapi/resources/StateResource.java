package com.devdavicosta.teaminfoapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.StateService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService service;
	
	@GetMapping
	@Operation(summary="Traz todos os estados.")
	public ResponseEntity<List<State>> findAll() {
		List<State> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o estado pelo id.")
	public ResponseEntity<State> findById(@PathVariable Long id) {
		State obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) estado(s) pelo nome.")
	public ResponseEntity<List<State>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<State> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/countrysearch")
	@Operation(summary="Traz os estados pelo pais.")
	public ResponseEntity<List<State>> findByCountry(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<State> list = service.findByCountry(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Operation(summary="Insere um estado.")
	public ResponseEntity<State> insert(@RequestBody State obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o estado pelo id.")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody State obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o estado pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}