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

import com.devdavicosta.teaminfoapi.entities.Position;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.PositionService;

@RestController
@RequestMapping(value="/positions")
public class PositionResource {
	
	@Autowired
	private PositionService service;
	
	@GetMapping
	public ResponseEntity<List<Position>> findAll() {
		List<Position> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Position> findById(@PathVariable Long id) {
		Position obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	public ResponseEntity<List<Position>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Position> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Position> insert(@RequestBody Position obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Position> update(@PathVariable Long id, @RequestBody Position obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}