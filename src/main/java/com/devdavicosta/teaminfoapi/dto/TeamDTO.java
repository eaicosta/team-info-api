package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.devdavicosta.teaminfoapi.entities.Coach;
import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Team;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String nome_popular;
	private String escudo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="GMT-3")
	private LocalDate data_fundacao;
	
	private String hino;
	private Country pais;
	private StateDTO estado;
	private StadiumDTO estadio;
	private Coach tecnico;
	
	public TeamDTO() {
	}
	
	public TeamDTO(Long id, String nome, String nome_popular, String escudo, LocalDate data_fundacao, String hino,
			Country pais, StateDTO estado, StadiumDTO estadio, Coach tecnico) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.escudo = escudo;
		this.data_fundacao = data_fundacao;
		this.hino = hino;
		this.pais = pais;
		this.estado = estado;
		this.estadio = estadio;
		this.tecnico = tecnico;
	}
	
	public TeamDTO(Long id, String nome, String nome_popular, StateDTO estado) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.estado = estado;
	}

	public TeamDTO(Long id, String nome, String nome_popular, Country pais) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.pais = pais;
	}

	public TeamDTO(Long id, String nome, String nome_popular, Coach tecnico) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.tecnico = tecnico;
	}

	public TeamDTO(Long id, String nome, String nome_popular) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
	}

	public TeamDTO(Long id, String nome_popular) {
		this.id = id;
		this.nome_popular = nome_popular;
	}
	
	public TeamDTO(Team obj) {
		this.id = obj.getId();
		this.nome_popular = obj.getNome_popular();
		this.pais = obj.getPais();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome_popular() {
		return nome_popular;
	}

	public void setNome_popular(String nome_popular) {
		this.nome_popular = nome_popular;
	}

	public String getEscudo() {
		return escudo;
	}

	public void setEscudo(String escudo) {
		this.escudo = escudo;
	}

	public LocalDate getData_fundacao() {
		return data_fundacao;
	}

	public void setData_fundacao(LocalDate data_fundacao) {
		this.data_fundacao = data_fundacao;
	}

	public String getHino() {
		return hino;
	}

	public void setHino(String hino) {
		this.hino = hino;
	}

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
	}

	public StateDTO getEstado() {
		return estado;
	}

	public void setEstado(StateDTO estado) {
		this.estado = estado;
	}

	public StadiumDTO getEstadio() {
		return estadio;
	}

	public void setEstadio(StadiumDTO estadio) {
		this.estadio = estadio;
	}

	public Coach getTecnico() {
		return tecnico;
	}

	public void setTecnico(Coach tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamDTO other = (TeamDTO) obj;
		return Objects.equals(id, other.id);
	}
}