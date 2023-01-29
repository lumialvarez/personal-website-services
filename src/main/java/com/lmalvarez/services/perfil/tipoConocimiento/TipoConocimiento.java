package com.lmalvarez.services.perfil.tipoConocimiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "TipoConocimiento")
@Table(name = "tipo_conocimiento")
public class TipoConocimiento {
	@Id
	@SequenceGenerator(name = "tipo_conocimiento_sequence", sequenceName = "tipo_conocimiento_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_conocimiento_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	public TipoConocimiento() {
	}

	public TipoConocimiento(@NotBlank(message = "Campo nombre requerido") String nombre) {
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

	@Override
	public String toString() {
		return "TipoConocimiento [id=" + id + ", nombre=" + nombre + "]";
	}
}
