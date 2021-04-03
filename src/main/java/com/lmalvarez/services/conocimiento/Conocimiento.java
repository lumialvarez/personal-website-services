package com.lmalvarez.services.conocimiento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "Conocimiento")
@Table(name = "conocimiento")
public class Conocimiento {
	@Id
	@SequenceGenerator(name = "conocimiento_sequence", sequenceName = "conocimiento_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conocimiento_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(nullable = false)
	private String nombre;
	@NotBlank(message = "Campo tipo requerido")
	@Column(nullable = false)
	private String tipo;
	@NotNull(message = "Campo nivel requerido")
	@Column(nullable = false)
	private Integer nivel;
	@Column(nullable = true)
	private String descripcion;

	public Conocimiento() {
	}

	public Conocimiento(@NotBlank(message = "Campo nombre requerido") String nombre,
			@NotBlank(message = "Campo tipo requerido") String tipo,
			@NotBlank(message = "Campo nivel requerido") Integer nivel,
			String descripcion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.descripcion = descripcion;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
