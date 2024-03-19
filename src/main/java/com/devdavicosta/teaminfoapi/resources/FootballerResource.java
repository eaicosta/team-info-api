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

import com.devdavicosta.teaminfoapi.dto.FootballerDTO;
import com.devdavicosta.teaminfoapi.entities.Footballer;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.FootballerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/footballers")
public class FootballerResource {
	
	@Autowired
	private FootballerService service;
	
	@GetMapping
	@Operation(summary="Traz todos os jogadores.")
	public ResponseEntity<List<FootballerDTO>> findAll() {
		List<FootballerDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o jogador pelo id.")
	public ResponseEntity<Footballer> findById(@PathVariable Long id) {
		Footballer obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) jogador(es) pelo nome.")
	public ResponseEntity<List<FootballerDTO>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<FootballerDTO> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/positionsearch")
	@Operation(summary="Traz os jogadores pela posição.")
	public ResponseEntity<List<FootballerDTO>> findByPosition(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<FootballerDTO> list = service.findByPosition(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/teamsearch")
	@Operation(summary="Traz os jogadores pelo time.")
	public ResponseEntity<List<FootballerDTO>> findByTeam(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<FootballerDTO> list = service.findByTeam(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/countrysearch")
	@Operation(summary="Traz os jogadores pelo pais.")
	public ResponseEntity<List<FootballerDTO>> findByCountry(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<FootballerDTO> list = service.findByCountry(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Operation(summary="Insere um jogador.")
	public ResponseEntity<Footballer> insert(@RequestBody Footballer obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o jogador pelo id.")
	public ResponseEntity<Footballer> update(@PathVariable Long id, @RequestBody Footballer obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o jogador pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}