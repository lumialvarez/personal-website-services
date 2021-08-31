package com.lmalvarez.services.notificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.services.security.rol.RolService;

@Service
public class NotificacionService {
	@Autowired
	private NotificacionRepository notificacionRepository;
	
	@Autowired
	private UsuarioNotificacionRepository usuarioNotificacionRepository;
	
	@Autowired
	private RolService rolService;
	
	public NotificacionService() {
		super();
	}
	
	public List<UsuarioNotificacion> getUnreadNotificacion(){
		return usuarioNotificacionRepository.findByLecturaFalse();
	}
	
	public void nuevaNotificacion(Notificacion notificacion) {
		notificacion = notificacionRepository.save(notificacion);
		
	}
}
