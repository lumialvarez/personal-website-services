package com.lmalvarez.services.notificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.security.usuario.Usuario;
import com.lmalvarez.services.security.usuario.UsuarioService;

@Service
public class NotificacionService {
	@Autowired
	private NotificacionRepository notificacionRepository;

	@Autowired
	private UsuarioNotificacionRepository usuNotificacionRepository;

	@Autowired
	private UsuarioService usuarioService;

	public NotificacionService() {
		super();
	}

	public Notificacion getNotificacionById(long id) {
		Notificacion notificacion = notificacionRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Notificacion con id " + id + " no existe"));
		return notificacion;
	}

	public List<Notificacion> getNotificaciones() {
		return notificacionRepository.findAll();
	}

	public List<Notificacion> getUnreadNotificacion() {
		return notificacionRepository.findByReadStatus(false);
	}

	public List<Notificacion> getUnreadNotificacionByUsuario(int usuarioId) {
		return notificacionRepository.findByReadStatusAndUsuario(false, usuarioId);
	}

	public void nuevaNotificacion(Notificacion notificacion) {
		List<Usuario> lstUsu = usuarioService.getUsuariosAdmin();
		for (Usuario usuario : lstUsu) {
			UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
			usuarioNotificacion.setNotificacion(notificacion);
			usuarioNotificacion.setUsuario(usuario);
			usuarioNotificacion.setRead(false);
			usuNotificacionRepository.save(usuarioNotificacion);
		}
	}

	public void setNotificacionRead(long notificacionId, int usuarioId) {
		Notificacion notificacion = getNotificacionById(notificacionId);
		for (UsuarioNotificacion un : notificacion.getUsuarioNotificaciones()) {
			if (un.getUsuario().getId() == usuarioId) {
				un.setRead(true);
			}
		}
		notificacionRepository.save(notificacion);

	}
}
