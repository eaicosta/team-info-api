package com.devdavicosta.teaminfoapi.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="estadios")
public class Stadium implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nome_popular;
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private State estado;
	
	public Stadium() {
	}

	public Stadium(Long id, @NotBlank String nome, @NotBlank String nome_popular, State estado) {
		this.id = id;
		this.nome = nome;
		this.nome_popular = nome_popular;
		this.estado = estado;
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

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
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
		Stadium other = (Stadium) obj;
		return Objects.equals(id, other.id);
	}
}