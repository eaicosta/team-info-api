package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Team;

public class TeamDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome_popular;
	private Country pais;
	
	public TeamDTO() {
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

	public String getNome_popular() {
		return nome_popular;
	}

	public void setNome_popular(String nome_popular) {
		this.nome_popular = nome_popular;
	}

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
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