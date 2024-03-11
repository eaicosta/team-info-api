package com.devdavicosta.teaminfoapi.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Footballer;
import com.devdavicosta.teaminfoapi.entities.Position;
import com.devdavicosta.teaminfoapi.entities.Stadium;
import com.devdavicosta.teaminfoapi.entities.State;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.devdavicosta.teaminfoapi.entities.Tournament;
import com.devdavicosta.teaminfoapi.repositories.CoachRepository;
import com.devdavicosta.teaminfoapi.repositories.CountryRepository;
import com.devdavicosta.teaminfoapi.repositories.FootballerRepository;
import com.devdavicosta.teaminfoapi.repositories.PositionRepository;
import com.devdavicosta.teaminfoapi.repositories.StadiumRepository;
import com.devdavicosta.teaminfoapi.repositories.StateRepository;
import com.devdavicosta.teaminfoapi.repositories.TeamRepository;
import com.devdavicosta.teaminfoapi.repositories.TournamentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CoachRepository coachRepository;
	
	@Autowired
	private StadiumRepository stadiumRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private FootballerRepository footballerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Country br = new Country(null, "Brasil");
		Country pt = new Country(null, "Portugal");
		Country ar = new Country(null, "Argentina");
		countryRepository.saveAll(Arrays.asList(br, pt, ar));
		
		
		State rj = new State(null, br, "Rio de Janeiro", "RJ");
		State sp = new State(null, br, "São Paulo", "SP");
		State go = new State(null, br, "Goiás", "GO");
		State mg = new State(null, br, "Minas Gerais", "MG");	
		stateRepository.saveAll(Arrays.asList(rj, sp, go, mg));
		
		Coach tite = new Coach(null, "Tite", br);
		Coach abel = new Coach(null, "Abel Ferreira", pt);
		Coach ze = new Coach(null, "Zé Ricardo", br);
		Coach nicolas = new Coach(null, "Nicolas Larcamon", ar);
		Coach diniz = new Coach(null, "Fernando Diniz", br);
		coachRepository.saveAll(Arrays.asList(tite, abel, ze, nicolas, diniz));
		
		Stadium maracana = new Stadium(null, "Estádio Jornalista Mário Filho", "Maracanã", rj);
		Stadium allianz = new Stadium(null, "Allianz Parque", "Arena Palmeiras", sp);
		Stadium serrinha = new Stadium(null, "Estádio Hailé Pinheiro", "Serrinha", go);
		Stadium mineirao = new Stadium(null, "Estádio Governador Magalhães Pinto", "Mineirão", mg);
		stadiumRepository.saveAll(Arrays.asList(maracana, allianz, serrinha, mineirao));
		
		Team flamengo = new Team(null, "Clube de Regatas do Flamengo", "Flamengo", "https://logodetimes.com/wp-content/uploads/flamengo.png", 
				br, LocalDate.parse("1895-11-15"), "https://www.letras.mus.br/flamengo/224185", rj, maracana, tite);
		Team palmeiras = new Team(null, "Sociedade Esportiva Palmeiras", "Palmeiras", "https://logodetimes.com/wp-content/uploads/palmeiras.png", 
				br, LocalDate.parse("1914-08-26"), "https://www.letras.mus.br/palmeiras/397875", sp, allianz, abel);
		Team goias = new Team(null, "Goiás Esporte Clube", "Goiás", "https://logodetimes.com/wp-content/uploads/goias-esporte-clube.png", 
				br, LocalDate.parse("1943-04-06"), "https://www.letras.mus.br/hinos-de-futebol/1780776", go, serrinha, ze);
		Team cruzeiro = new Team(null, "Cruzeiro Esporte Clube", "Cruzeiro", "https://logodetimes.com/wp-content/uploads/cruzeiro.png", 
				br,LocalDate.parse("1921-01-02"), "https://www.letras.mus.br/cruzeiro/hino-do-cruzeiro",  mg, mineirao, nicolas);
		teamRepository.saveAll(Arrays.asList(flamengo, palmeiras, goias, cruzeiro));
		
		flamengo.getRivais().add(palmeiras);
		flamengo.getRivais().add(cruzeiro);
		palmeiras.getRivais().add(flamengo);
		cruzeiro.getRivais().add(goias);
		teamRepository.saveAll(Arrays.asList(flamengo, palmeiras, cruzeiro));
		
		Tournament brasileirao19 = new Tournament(null, "Campeonato Brasileiro", "2019", flamengo);
		Tournament libertadores19 = new Tournament(null, "Libertadores", "2019", flamengo);
		Tournament brasileirao20 = new Tournament(null, "Campeonato Brasileiro", "2020", flamengo);
		Tournament libertadores20 = new Tournament(null, "Libertadores", "2020", palmeiras);
		tournamentRepository.saveAll(Arrays.asList(brasileirao19, brasileirao20, libertadores19, libertadores20));
		
		Position ata = new Position(null, "Atacante", "ATA");
		Position pe = new Position(null, "Ponta Esquerda", "PE");
		Position gol = new Position(null, "Goleiro", "GOL");
		positionRepository.saveAll(Arrays.asList(ata, pe, gol));
		
		Footballer gabigol = new Footballer(null, "Gabriel Barbosa", ata, br, flamengo);
		Footballer cebolinha = new Footballer(null, "Everton Cebolinha", pe, br, flamengo);
		Footballer weverton = new Footballer(null, "Weverton", gol, br, palmeiras);
		footballerRepository.saveAll(Arrays.asList(gabigol, cebolinha, weverton));
	}
}
