package com.lmalvarez.services.notificacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
	
	@OneToMany(mappedBy = "notificacion")
	private Set<UsuarioNotificacion> usuarioNotificaciones = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Set<UsuarioNotificacion> getUsuarioNotificaciones() {
		return usuarioNotificaciones;
	}
	public void setUsuarioNotificaciones(Set<UsuarioNotificacion> usuarioNotificaciones) {
		this.usuarioNotificaciones = usuarioNotificaciones;
	}
	
	
	public static NotificacionDto toDto(Notificacion notificacion) {
		NotificacionDto dto = new NotificacionDto();
		dto.setId(notificacion.getId());
		dto.setDetalle(notificacion.getDetalle());
		return dto;
	}
	
	public static List<NotificacionDto> toLstDto(List<Notificacion> lstNotificacion){
		List<NotificacionDto> lstNotificacionDto = new ArrayList<>();
		for(Notificacion notificacion : lstNotificacion) {
			lstNotificacionDto.add(toDto(notificacion));
		}
		return lstNotificacionDto;
	}
	
}
