package com.lmalvarez.services.security.rol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Rol")
@Table(name = "rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;
	@NotNull(message = "Campo nombre es requerido")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RolNombre rolNombre;

	public Rol() {
	}

	public Rol(@NotNull RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}
}
