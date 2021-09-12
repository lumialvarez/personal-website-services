	package com.lmalvarez.services.notificacion;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@GetMapping(path = "/read")
	public List<NotificacionDto> getReadNotificacionByUsuario() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return notificacionService.getReadNotificacionByUsuario(userDetails.getUsername());
	}

	@GetMapping(path = "/unread")
	public List<NotificacionDto> getUnreadNotificacionByUsuario() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return notificacionService.getUnreadNotificacionByUsuario(userDetails.getUsername());
	}

	@PostMapping
	public void nuevaNotificacion(@Valid @RequestBody NuevaNotificacionDto nuevaNotificacionDto) {
		notificacionService.nuevaNotificacion(nuevaNotificacionDto);
	}

	@PutMapping(path = "/{notificacionId}/read")
	public void setNotificacionRead(@PathVariable("notificacionId") long notificacionId) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		notificacionService.setNotificacionRead(notificacionId, userDetails.getUsername());
	}

}
