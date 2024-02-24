package com.devdavicosta.teaminfoapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.repositories.CoachRepository;
import com.devdavicosta.teaminfoapi.repositories.StadiumRepository;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired StadiumRepository stadiumRepository;

	@Override
	public void run(String... args) throws Exception {
		State rj = new State(null, "Rio de Janeiro", "RJ");
		State sp = new State(null, "São Paulo", "SP");
		State go = new State(null, "Goiás", "GO");
		State mg = new State(null, "Minas Gerais", "MG");	
		stateRepository.saveAll(Arrays.asList(rj, sp, go, mg));
		
		Coach tite = new Coach(null, "Tite", "Brasileiro");
		Coach abel = new Coach(null, "Abel Ferreira", "Português");
		Coach ze = new Coach(null, "Zé Ricardo", "Brasileiro");
		Coach nicolas = new Coach(null, "Nicolas Larcamon", "Argentino");
		coachRepository.saveAll(Arrays.asList(tite, abel, ze, nicolas));
		
		Stadium maracana = new Stadium(null, "Estádio Jornalista Mário Filho", "Maracanã", rj);
		Stadium itaquerao = new Stadium(null, "Neo Química Arena", "Itaquerão", sp);
		Stadium serrinha = new Stadium(null, "Estádio Serra Dourada", "Serrinha", go);
		Stadium mineirao = new Stadium(null, "Estádio Governador Magalhães Pinto", "Mineirão", mg);
		stadiumRepository.saveAll(Arrays.asList(maracana, itaquerao, serrinha, mineirao));
	}
}
