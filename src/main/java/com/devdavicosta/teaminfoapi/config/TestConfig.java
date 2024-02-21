package com.devdavicosta.teaminfoapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private StateRepository stateRepository;

	@Override
	public void run(String... args) throws Exception {
		State rj = new State(null, "Rio de Janeiro", "RJ");
		State sp = new State(null, "São Paulo", "SP");
		State go = new State(null, "Goiás", "GO");
		
		stateRepository.saveAll(Arrays.asList(rj, sp, go));
		
	}
}
