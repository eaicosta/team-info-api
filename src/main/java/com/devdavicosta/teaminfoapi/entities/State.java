package com.devdavicosta.teaminfoapi.entities;

import java.io.Serializable;
import java.util.Objects;

public class State implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String uf;
	
	public State() {
	}

	public State(Long id, String name, String uf) {
		this.id = id;
		this.name = name;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		State other = (State) obj;
		return Objects.equals(id, other.id);
	}
}
