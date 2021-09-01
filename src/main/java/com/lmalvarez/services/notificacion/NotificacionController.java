package com.lmalvarez.services.notificacion;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/int/v1/notificacion")
public class NotificacionController {
	@Autowired
	private NotificacionService notificacionService;

	@GetMapping
	public List<Notificacion> getNotificaciones() {
		return notificacionService.getNotificaciones();
	}

	@GetMapping(path = "/unread")
	public List<Notificacion> getUnreadNotificacion() {
		return notificacionService.getUnreadNotificacion();
	}

	@GetMapping(path = "/unread/{usuarioId}")
	public List<Notificacion> getUnreadNotificacionByUsuario(@PathVariable("usuarioId") int usuarioId) {
		return notificacionService.getUnreadNotificacionByUsuario(usuarioId);
	}

	@PostMapping
	public void nuevaNotificacion(@Valid @RequestBody Notificacion notificacion) {
		notificacionService.nuevaNotificacion(notificacion);
	}

	@PutMapping(path = "/{notificacionId}/read/{usuarioId}")
	public void setNotificacionRead(@PathVariable("notificacionId") long notificacionId,
			@PathVariable("usuarioId") int usuarioId) {
		notificacionService.setNotificacionRead(notificacionId, usuarioId);
	}

}
