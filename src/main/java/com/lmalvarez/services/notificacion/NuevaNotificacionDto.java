package com.lmalvarez.services.notificacion;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class NuevaNotificacionDto {
	@NotBlank(message = "Campo detalle es requerido")
    private String detalle;
    
    private List<String> usuarios;

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}
    
    
}
