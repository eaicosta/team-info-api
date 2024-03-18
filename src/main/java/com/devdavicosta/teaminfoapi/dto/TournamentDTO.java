package com.devdavicosta.teaminfoapi.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String ano_edicao;
	private TeamDTO time_campeao;
	
	public TournamentDTO() {
	}

	public TournamentDTO(Long id, String nome, String ano_edicao, TeamDTO time_campeao) {
		this.id = id;
		this.nome = nome;
		this.ano_edicao = ano_edicao;
		this.time_campeao = time_campeao;
	}

	public TournamentDTO(Long id, String nome, String ano_edicao) {
		this.id = id;
		this.nome = nome;
		this.ano_edicao = ano_edicao;
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

	public TeamDTO getTime_campeao() {
		return time_campeao;
	}

	public void setTime_campeao(TeamDTO time_campeao) {
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
		TournamentDTO other = (TournamentDTO) obj;
		return Objects.equals(id, other.id);
	}
}