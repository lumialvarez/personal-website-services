package com.lmalvarez.services.perfil.idiomaPerfil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "IdiomaPerfil")
@Table(name = "idioma_perfil")
public class IdiomaPerfil {
	@Id
	@SequenceGenerator(name = "idioma_perfil_sequence", sequenceName = "idioma_perfil_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_perfil_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	public IdiomaPerfil() {
	}

	public IdiomaPerfil(@NotBlank(message = "Campo nombre requerido") String nombre) {
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
		return "IdiomaPerfil [id=" + id + ", nombre=" + nombre + "]";
	}
}
