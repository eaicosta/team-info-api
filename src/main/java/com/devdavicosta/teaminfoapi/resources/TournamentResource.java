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

import com.devdavicosta.teaminfoapi.entities.Tournament;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.TournamentService;

@RestController
@RequestMapping(value="/tournaments")
public class TournamentResource {

	@Autowired
	private TournamentService service;
	
	@GetMapping
	public ResponseEntity<List<Tournament>> findAll() {
		List<Tournament> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Tournament> findById(@PathVariable Long id) {
		Tournament obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	public ResponseEntity<List<Tournament>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Tournament> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/yearsearch")
	public ResponseEntity<List<Tournament>> findByYear(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Tournament> list = service.findByYear(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/championsearch")
	public ResponseEntity<List<Tournament>> findByChampion(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Tournament> list = service.findByChampion(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Tournament> insert(@RequestBody Tournament obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Tournament> update(@PathVariable Long id, @RequestBody Tournament obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
