package com.lmalvarez.services.notificacion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.lmalvarez.services.security.usuario.Usuario;

@Entity(name = "UsuarioNotificacion")
@Table(name = "usuario_notificacion")
public class UsuarioNotificacion {
	@Id
	@SequenceGenerator(name = "usuario_notificacion_sequence", sequenceName = "usuario_notificacion_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_notificacion_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@Valid
	@ManyToOne(cascade = { CascadeType.DETACH }, optional = false)
	@JoinColumn(name = "notificacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_usuario_notificacion_a_notificacion"))
	private Notificacion notificacion;
	@Valid
	@ManyToOne(cascade = { CascadeType.DETACH }, optional = false)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_usuario_notificacion_a_usuario"))
	private Usuario usuario;
	@NotNull(message = "Campo estado requerido")
	@Column(name = "lectura", nullable = false)
	private Boolean lectura;
}
