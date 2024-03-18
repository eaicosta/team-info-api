package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.devdavicosta.teaminfoapi.entities.Team;

public class RivalDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome_popular;
	private Set<TeamDTO> rivais;
	
	public RivalDTO() {
	}
	
	public RivalDTO(Team obj) {
		this.id = obj.getId();
		this.nome_popular = obj.getNome_popular();
		this.rivais = obj.getRivais().stream().map(team -> new TeamDTO(team.getId(), team.getNome() ,team.getNome_popular())).collect(Collectors.toSet());
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

	public Set<TeamDTO> getRivais() {
		return rivais;
	}

	public void setRivais(Set<TeamDTO> rivais) {
		this.rivais = rivais;
	}
}