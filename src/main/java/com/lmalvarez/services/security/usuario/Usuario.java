package com.lmalvarez.services.security.usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmalvarez.services.security.rol.Rol;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "usuario", uniqueConstraints = {
		@UniqueConstraint(name = "usuario_email_unique", columnNames = { "email" }),
		@UniqueConstraint(name = "usuario_nombre_usuario_unique", columnNames = { "nombreUsuario" }) })
public class Usuario {
	@Id
	@SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
	@Column(name = "id", updatable = false)
	private int id;
	@NotNull(message = "Campo nombre es requerido")
	@Column(nullable = false)
	private String nombre;
	@NotNull(message = "Campo nombreUsuario es requerido")
	@Column(nullable = false)
	private String nombreUsuario;
	@NotNull(message = "Campo email es requerido")
	@Column(nullable = false)
	private String email;
	@JsonIgnore
	@NotNull(message = "Campo password es requerido")
	@Column(nullable = false)
	private String password;
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"), foreignKey = @ForeignKey(name = "FK_usuario_to_usuario_rol"), inverseForeignKey = @ForeignKey(name = "FK_rol_to_usuario_rol"))
	private Set<Rol> roles = new HashSet<>();

	public Usuario() {
	}

	public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email,
			@NotNull String password) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
