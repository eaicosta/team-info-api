package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Set;

import com.devdavicosta.teaminfoapi.entities.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RivalDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome_popular;
	@JsonIgnoreProperties(value={"nome", "escudo", "data_fundacao", 
		"hino", "estado", "estadio", "tecnico"})
	private Set<Team> rivais;
	
	public RivalDTO() {
	}
	
	public RivalDTO(Team obj) {
		this.id = obj.getId();
		this.nome_popular = obj.getNome_popular();
		this.rivais = obj.getRivais();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_popular() {
		return nome_popular;
	}

	public void setNome_popular(String nome_popular) {
		this.nome_popular = nome_popular;
	}

	public Set<Team> getRivais() {
		return rivais;
	}

	public void setRivais(Set<Team> rivais) {
		this.rivais = rivais;
	}
}