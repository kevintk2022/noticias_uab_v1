package com.sistema.uab.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class genero {

	@Id
	@Column(name = "id_genero")
	private Integer id;

	private String titulo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public genero(Integer id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public genero() {
		super();
	}

	public genero(String titulo) {
		super();
		this.titulo = titulo;
	}

	public genero(Integer id) {
		super();
		this.id = id;
	}

}
