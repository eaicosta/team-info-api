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

import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.CountryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/countries")
public class CountryResource {
	
	@Autowired
	private CountryService service;
	
	@GetMapping
	@Operation(summary="Traz todos os paises.")
	public ResponseEntity<List<Country>> findAll() {
		List<Country> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o pais pelo id.")
	public ResponseEntity<Country> findById(@PathVariable Long id) {
		Country obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) pais(es) pelo nome.")
	public ResponseEntity<List<Country>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Country> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Operation(summary="Insere um pais.")
	public ResponseEntity<Country> insert(@RequestBody Country obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o pais pelo id.")
	public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o pais pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}