package com.devdavicosta.teaminfoapi.entities;

import java.util.Objects;

import com.devdavicosta.teaminfoapi.dto.TeamDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="jogadores")
public class Footballer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_posicao")
	private Position posicao;
	
	@ManyToOne
	@JoinColumn(name="id_pais")
	private Country pais;
	
	@ManyToOne
	@JoinColumn(name="id_time")
	private Team time;

	public Footballer() {
	}

	public Footballer(Long id, @NotBlank String nome, Position posicao, Country pais, Team time) {
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
		return new TeamDTO(time.getId(), time.getNome_popular());
	}

	public void setTime(Team time) {
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
		Footballer other = (Footballer) obj;
		return Objects.equals(id, other.id);
	}
}