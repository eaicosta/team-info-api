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

import com.devdavicosta.teaminfoapi.dto.TournamentDTO;
import com.devdavicosta.teaminfoapi.entities.Tournament;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.TournamentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/tournaments")
public class TournamentResource {

	@Autowired
	private TournamentService service;
	
	@GetMapping
	@Operation(summary="Traz todos os campeonatos.")
	public ResponseEntity<List<TournamentDTO>> findAll() {
		List<TournamentDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o campeonato pelo id.")
	public ResponseEntity<Tournament> findById(@PathVariable Long id) {
		Tournament obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) campeonato(s) pelo nome.")
	public ResponseEntity<List<TournamentDTO>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TournamentDTO> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/yearsearch")
	@Operation(summary="Traz os campeonatos pelo ano da edição.")
	public ResponseEntity<List<TournamentDTO>> findByYear(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TournamentDTO> list = service.findByYear(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/championsearch")
	@Operation(summary="Traz os campeonatos pelos times campeões.")
	public ResponseEntity<List<TournamentDTO>> findByChampion(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TournamentDTO> list = service.findByChampion(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Operation(summary="Insere um campeonato.")
	public ResponseEntity<Tournament> insert(@RequestBody Tournament obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o campeonato pelo id.")
	public ResponseEntity<Tournament> update(@PathVariable Long id, @RequestBody Tournament obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o campeonato pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
