package com.devdavicosta.teaminfoapi.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="campeonatos")
public class Tournament implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String ano_edicao;
	
	@ManyToOne
	@JsonIgnoreProperties(value={"nome", "escudo", "data_fundacao", 
				"hino", "estado", "estadio", "tecnico"})
	@JoinColumn(name="id_time")
	private Team time_campeao;
	
	public Tournament() {
	}

	public Tournament(Long id, @NotBlank String nome, @NotBlank String ano_edicao, Team time_campeao) {
		this.id = id;
		this.nome = nome;
		this.ano_edicao = ano_edicao;
		this.time_campeao = time_campeao;
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

	public String getAno_edicao() {
		return ano_edicao;
	}

	public void setAno_edicao(String ano_edicao) {
		this.ano_edicao = ano_edicao;
	}

	public Team getTime_campeao() {
		return time_campeao;
	}

	public void setTime_campeao(Team time_campeao) {
		this.time_campeao = time_campeao;
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
		Tournament other = (Tournament) obj;
		return Objects.equals(id, other.id);
	}
}