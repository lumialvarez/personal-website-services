package com.lmalvarez.services.perfil.proyecto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "Proyecto")
@Table(name = "proyecto", uniqueConstraints = {
		@UniqueConstraint(name = "proyecto_nombre_unique", columnNames = { "nombre" }) })
public class Proyecto {
	@Id
	@SequenceGenerator(name = "proyecto_sequence", sequenceName = "proyecto_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proyecto_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@NotBlank(message = "Campo descripcion requerido")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	@Column(name = "detalle", columnDefinition = "TEXT")
	private String detalle;
	@NotBlank(message = "img nombre requerido")
	@Column(name = "img", nullable = false)
	private String img;

	public Proyecto() {
	}

	public Proyecto(@NotBlank(message = "Campo nombre requerido") String nombre,
			@NotBlank(message = "Campo descripcion requerido") String descripcion, String detalle,
			@NotBlank(message = "img nombre requerido") String img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.detalle = detalle;
		this.img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
