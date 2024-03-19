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

import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.resources.util.URL;
import com.devdavicosta.teaminfoapi.services.StadiumService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value="/stadiums")
public class StadiumResource {
	
	@Autowired
	private StadiumService service;

	@GetMapping
	@Operation(summary="Traz todos os estádios.")
	public ResponseEntity<List<Stadium>> findAll() {
		List<Stadium> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	@Operation(summary="Traz o estádio pelo id.")
	public ResponseEntity<Stadium> findById(@PathVariable Long id) {
		Stadium obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/namesearch")
	@Operation(summary="Traz o(s) estádio(s) pelo nome.")
	public ResponseEntity<List<Stadium>> findByName(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Stadium> list = service.findByName(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/statesearch")
	@Operation(summary="Traz os estádios pelo estado.")
	public ResponseEntity<List<Stadium>> findByState(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Stadium> list = service.findByState(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	@Operation(summary="Insere um estádio.")
	public ResponseEntity<Stadium> insert(@RequestBody Stadium obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	@Operation(summary="Atualiza o estádio pelo id.")
	public ResponseEntity<Stadium> update(@PathVariable Long id, @RequestBody Stadium obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deleta o estádio pelo id.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}