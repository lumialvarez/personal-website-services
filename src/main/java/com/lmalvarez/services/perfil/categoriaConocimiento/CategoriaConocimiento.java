package com.lmalvarez.services.perfil.categoriaConocimiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "CategoriaConocimiento")
@Table(name = "categoria_conocimiento")
public class CategoriaConocimiento {
	@Id
	@SequenceGenerator(name = "categoria_conocimiento_sequence", sequenceName = "categoria_conocimiento_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_conocimiento_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public CategoriaConocimiento() {
	}

	public CategoriaConocimiento(@NotBlank(message = "Campo nombre requerido") String nombre) {
		super();
		this.nombre = nombre;
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
}
