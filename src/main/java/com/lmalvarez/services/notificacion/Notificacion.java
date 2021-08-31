package com.lmalvarez.services.notificacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Notificacion")
@Table(name = "notificacion")
public class Notificacion {
	@Id
	@SequenceGenerator(name = "notificacion_sequence", sequenceName = "notificacion_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificacion_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "detalle", columnDefinition = "TEXT")
	private String detalle;
}
