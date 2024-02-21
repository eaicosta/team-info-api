package com.devdavicosta.teaminfoapi.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devdavicosta.teaminfoapi.entities.State;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@GetMapping
	public ResponseEntity<State> findAll() {
		State s = new State(1L, "Rio de Janeiro", "RJ");
		return ResponseEntity.ok().body(s);
	}
}
