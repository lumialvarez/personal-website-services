package com.lmalvarez.services.notificacion;

import java.util.ArrayList;
import java.util.List;

import com.lmalvarez.services.security.rol.RolNombre;
import com.lmalvarez.services.security.usuario.UsuarioRepository;
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
	private UsuarioRepository usuarioRepository;

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
	
	public List<NotificacionDto> getReadNotificacionByUsuario(String nombreUsuario) {
		Usuario usuarioActivo = getByNombreUsuario(nombreUsuario);
		List<Notificacion> notificaciones = notificacionRepository.findByReadStatusAndUsuario(true, usuarioActivo.getId());
		return Notificacion.toLstDto(notificaciones);
	}

	public List<NotificacionDto> getUnreadNotificacionByUsuario(String nombreUsuario) {
		Usuario usuarioActivo = getByNombreUsuario(nombreUsuario);
		List<Notificacion> notificaciones = notificacionRepository.findByReadStatusAndUsuario(false, usuarioActivo.getId());
		return Notificacion.toLstDto(notificaciones);
	}
	
	public void nuevaNotificacion(String detalle) {
		    NuevaNotificacionDto nuevaNotificacionDto = new NuevaNotificacionDto();
			nuevaNotificacionDto.setDetalle(detalle);
			nuevaNotificacion(nuevaNotificacionDto);
	}

	public void nuevaNotificacion(NuevaNotificacionDto nuevaNotificacionDto) {
		List<Usuario> lstUsu;
		Notificacion notificacion = new Notificacion();
		notificacion.setDetalle(nuevaNotificacionDto.getDetalle());
		if(nuevaNotificacionDto.getUsuarios() != null && !nuevaNotificacionDto.getUsuarios().isEmpty()) {
			lstUsu = new ArrayList<>();
			for(String nombreUsuarioNuevaNotif : nuevaNotificacionDto.getUsuarios()) {
				UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
				usuarioNotificacion.setNotificacion(notificacion);
				usuarioNotificacion.setUsuario(getByNombreUsuario(nombreUsuarioNuevaNotif));
				usuarioNotificacion.setRead(false);
				notificacion.getUsuarioNotificaciones().add(usuarioNotificacion);
			}
		} else {
			lstUsu = getUsuariosAdmin();
			for (Usuario usuario : lstUsu) {
				UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
				usuarioNotificacion.setNotificacion(notificacion);
				usuarioNotificacion.setUsuario(usuario);
				usuarioNotificacion.setRead(false);
				notificacion.getUsuarioNotificaciones().add(usuarioNotificacion);
			}
		}
		notificacionRepository.save(notificacion);
		for(UsuarioNotificacion un : notificacion.getUsuarioNotificaciones()) {
			usuNotificacionRepository.save(un);
		}
	}

	public void setNotificacionRead(long notificacionId, String nombreUsuario) {
		Usuario usuarioActivo = getByNombreUsuario(nombreUsuario);
		Notificacion notificacion = getNotificacionById(notificacionId);
		boolean existeNotificacion = false;
		for (UsuarioNotificacion un : notificacion.getUsuarioNotificaciones()) {
			if (un.getUsuario().getId() == usuarioActivo.getId()) {
				existeNotificacion = true;
				un.setRead(true);
			}
		}
		if(!existeNotificacion) {
			throw new CustomNotFoundException("Relacion de notificacion y usuario no existe");
		}
		notificacionRepository.save(notificacion);
	}

	private Usuario getByNombreUsuario(String nombreUsuario) {
		Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
				.orElseThrow(() -> new CustomNotFoundException("Usuario " + nombreUsuario + " no existe"));
		return usuario;
	}

	private List<Usuario> getUsuariosAdmin(){
		return usuarioRepository.findByRol(RolNombre.ROLE_ADMIN);
	}
}
