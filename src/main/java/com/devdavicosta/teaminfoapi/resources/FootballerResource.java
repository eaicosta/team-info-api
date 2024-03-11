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

import com.devdavicosta.teaminfoapi.entities.Footballer;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.FootballerService;

@RestController
@RequestMapping(value="/footballers")
public class FootballerResource {
	
	@Autowired
	private FootballerService service;
	
	@GetMapping
	public ResponseEntity<List<Footballer>> findAll() {
		List<Footballer> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Footballer> findById(@PathVariable Long id) {
		Footballer obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	public ResponseEntity<List<Footballer>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Footballer> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/positionsearch")
	public ResponseEntity<List<Footballer>> findByPosition(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Footballer> list = service.findByPosition(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/teamsearch")
	public ResponseEntity<List<Footballer>> findByTeam(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Footballer> list = service.findByTeam(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/countrysearch")
	public ResponseEntity<List<Footballer>> findByCountry(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Footballer> list = service.findByCountry(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Footballer> insert(@RequestBody Footballer obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Footballer> update(@PathVariable Long id, @RequestBody Footballer obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}