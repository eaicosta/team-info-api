package com.devdavicosta.teaminfoapi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="times")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nome_popular;
	
	@NotBlank
	private String escudo;
	
	@ManyToOne
	@JoinColumn(name="id_pais")
	private Country pais;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="GMT-3")
	private LocalDate data_fundacao;
	
	@NotBlank
	private String hino;
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private State estado;
	
	@ManyToOne
	@JoinColumn(name="id_estadio")
	private Stadium estadio;
	
	@OneToOne
	@JoinColumn(name="id_tecnico")
	private Coach tecnico;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="times_rivais", 
	joinColumns=@JoinColumn(name="id_time"),
	inverseJoinColumns=@JoinColumn(name="id_rival") 
	)
	private Set<Team> rivais = new HashSet<>();
	
	public Team() {
	}

	public Team(Long id, @NotBlank String nome, @NotBlank String nome_popular, @NotBlank String escudo,
			Country pais, @NotBlank LocalDate data_fundacao, @NotBlank String hino, State estado,
			Stadium estadio, Coach tecnico) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.escudo = escudo;
		this.pais = pais;
		this.data_fundacao = data_fundacao;
		this.hino = hino;
		this.estado = estado;
		this.estadio = estadio;
		this.tecnico = tecnico;
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

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
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

	public Stadium getEstadio() {
		return estadio;
	}

	public void setEstadio(Stadium estadio) {
		this.estadio = estadio;
	}

	public Coach getTecnico() {
		return tecnico;
	}

	public void setTecnico(Coach tecnico) {
		this.tecnico = tecnico;
	}

	public Set<Team> getRivais() {
		return rivais;
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
		Team other = (Team) obj;
		return Objects.equals(id, other.id);
	}
}