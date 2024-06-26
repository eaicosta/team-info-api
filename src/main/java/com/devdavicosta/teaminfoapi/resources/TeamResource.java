package com.devdavicosta.teaminfoapi.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devdavicosta.teaminfoapi.dto.RivalDTO;
import com.devdavicosta.teaminfoapi.dto.TeamDTO;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.TeamService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/teams")
public class TeamResource {

	@Autowired
	private TeamService service;
	
	@GetMapping
	@Operation(summary="Traz todos os times.")
	public ResponseEntity<List<TeamDTO>> findAll() {
		List<TeamDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o time pelo id.")
	public ResponseEntity<Team> findById(@PathVariable Long id) {
		Team obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) time(s) pelo nome.")
	public ResponseEntity<List<TeamDTO>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TeamDTO> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/statesearch")
	@Operation(summary="Traz os times pelo estado.")
	public ResponseEntity<List<TeamDTO>> findByState(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TeamDTO> list = service.findByState(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/countrysearch")
	@Operation(summary="Traz os times pelo pais.")
	public ResponseEntity<List<TeamDTO>> findByCountry(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TeamDTO> list = service.findByCountry(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/coachsearch")
	@Operation(summary="Traz o time pelo técnico.")
	public ResponseEntity<List<TeamDTO>> findByCoach(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<TeamDTO> list = service.findByCoach(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/rivals/{id}")
	@Operation(summary="Traz os rivais de um time pelo id.")
	public ResponseEntity<RivalDTO> getRivals(@PathVariable Long id) {
		RivalDTO obj = service.getRivals(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Operation(summary="Insere um time.")
	public ResponseEntity<Team> insert(@RequestBody Team obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value="/rivals/{id}")
	@Operation(summary="Insere o rival de um time pelo id.")
	public ResponseEntity<RivalDTO> insertRivals(@PathVariable Long id, @RequestBody Long idRival) {
		RivalDTO rivalDTO = service.insertRivals(id, idRival);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rivalDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o time pelo id.")
	public ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PatchMapping(value="/{id}")
	@Operation(summary="Atualiza um ou mais atributos de um time.")
	public ResponseEntity<Team> updatePatch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		service.updatePatch(id, fields);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o time pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
