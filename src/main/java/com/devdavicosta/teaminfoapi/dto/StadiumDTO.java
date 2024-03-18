package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StadiumDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String nome_popular;
	
	public StadiumDTO() {
	}

	public StadiumDTO(Long id, String nome, String nome_popular) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
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
		StadiumDTO other = (StadiumDTO) obj;
		return Objects.equals(id, other.id);
	}
}