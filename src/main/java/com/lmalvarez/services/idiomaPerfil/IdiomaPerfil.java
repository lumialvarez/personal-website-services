package com.lmalvarez.services.idiomaPerfil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
