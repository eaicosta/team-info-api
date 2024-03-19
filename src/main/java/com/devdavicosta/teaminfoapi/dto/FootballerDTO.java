package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.devdavicosta.teaminfoapi.entities.Country;
import com.devdavicosta.teaminfoapi.entities.Position;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FootballerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Position posicao;
	private Country pais;
	private TeamDTO time;
	
	public FootballerDTO() {
	}

	public FootballerDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public FootballerDTO(Long id, String nome, Position posicao) {
		this.id = id;
		this.nome = nome;
		this.posicao = posicao;
	}

	public FootballerDTO(Long id, String nome, Country pais) {
		this.id = id;
		this.nome = nome;
		this.pais = pais;
	}

	public FootballerDTO(Long id, String nome, TeamDTO time) {
		this.id = id;
		this.nome = nome;
		this.time = time;
	}

	public FootballerDTO(Long id, String nome, Position posicao, Country pais, TeamDTO time) {
		this.id = id;
		this.nome = nome;
		this.posicao = posicao;
		this.pais = pais;
		this.time = time;
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

	public Position getPosicao() {
		return posicao;
	}

	public void setPosicao(Position posicao) {
		this.posicao = posicao;
	}

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
	}

	public TeamDTO getTime() {
		return time;
	}

	public void setTime(TeamDTO time) {
		this.time = time;
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
		FootballerDTO other = (FootballerDTO) obj;
		return Objects.equals(id, other.id);
	}
}