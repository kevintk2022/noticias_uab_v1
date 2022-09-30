package com.sistema.uab.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

@Entity

public class noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	
	private Integer id;
	@NotBlank
	
	private String titulo;
	
	@NotBlank
	
	private String contexto;
	

	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaPublicacion;

	@NotBlank
	private String youtubeVideoId;

	private String rutaPortada;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "genero_noticia", joinColumns = @JoinColumn(name = "id_noticia"), inverseJoinColumns = @JoinColumn(name = "id_genero"))

	private List<genero> generos;

	@Transient
	private MultipartFile portada;

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

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getYoutubeVideoId() {
		return youtubeVideoId;
	}

	public void setYoutubeVideoId(String youtubeVideoId) {
		this.youtubeVideoId = youtubeVideoId;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public List<genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<genero> generos) {
		this.generos = generos;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}

	public noticia() {
		super();
	}

	public noticia(Integer id, @NotBlank String titulo, @NotBlank String contexto, @NotNull LocalDate fechaPublicacion,
			@NotBlank String youtubeVideoId, String rutaPortada, @NotEmpty List<genero> generos,
			MultipartFile portada) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contexto = contexto;
		this.fechaPublicacion = fechaPublicacion;
		this.youtubeVideoId = youtubeVideoId;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.portada = portada;
	}

	public noticia(@NotBlank String titulo, @NotBlank String contexto, @NotNull LocalDate fechaPublicacion,
			@NotBlank String youtubeVideoId, String rutaPortada, @NotEmpty List<genero> generos,
			MultipartFile portada) {
		super();
		this.titulo = titulo;
		this.contexto = contexto;
		this.fechaPublicacion = fechaPublicacion;
		this.youtubeVideoId = youtubeVideoId;
		this.rutaPortada = rutaPortada;
		this.generos = generos;
		this.portada = portada;
	}

}
