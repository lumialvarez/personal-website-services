package com.lmalvarez.services.notificacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmalvarez.services.security.usuario.Usuario;

@Entity(name = "UsuarioNotificacion")
@Table(name = "usuario_notificacion")
public class UsuarioNotificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "notificacion_id")
	private Notificacion notificacion;

	@Column(nullable = false, columnDefinition = "boolean default false")
	boolean read;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

}
