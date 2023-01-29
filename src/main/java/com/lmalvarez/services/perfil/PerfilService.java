package com.lmalvarez.services.perfil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.perfil.conocimiento.Conocimiento;
import com.lmalvarez.services.perfil.conocimiento.ConocimientoService;
import com.lmalvarez.services.perfil.idiomaPerfil.IdiomaPerfilService;
import com.lmalvarez.services.perfil.proyecto.Proyecto;
import com.lmalvarez.services.perfil.proyecto.ProyectoService;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private ConocimientoService conocimientoService;
	
	@Autowired
	private IdiomaPerfilService idiomaPerfilService;

	public PerfilService() {
		super();
	}

	public List<Perfil> getPerfil() {
		return perfilRepository.findAll();
	}
	
	public List<Perfil> getPerfilActivo() {
		return perfilRepository.findByEstado(true);
	}

	public Perfil getPerfilById(Long id) {
		Perfil perfil = perfilRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Perfil con id " + id + " no existe"));
		return perfil;
	}

	public void nuevoPerfil(Perfil perfil) {
		perfilRepository.save(perfil);
	}

	public void eliminarPerfil(Long id) {
		boolean exists = perfilRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Perfil con id " + id + " no existe");
		}
		perfilRepository.deleteById(id);
	}

	@Transactional
	public void actualizarPerfil(Perfil in) {
		Perfil perfil = getPerfilById(in.getId());

		perfil.setNombre(in.getNombre());
		
		perfil.setProfesion(in.getProfesion());
		
		perfil.setPerfilProfesional(in.getPerfilProfesional());
		
		perfil.setPerfilPersonal(in.getPerfilPersonal());
		
		Set<Proyecto> copyLstProyecto = new HashSet<Proyecto>();
		for(Proyecto proyecto: in.getProyectos()) {
			try {
				proyectoService.getProyectoByNombre(proyecto.getNombre());
				copyLstProyecto.add(proyecto);
			} catch (CustomNotFoundException e) {
				Proyecto tmProyecto = proyectoService.nuevoProyecto(proyecto);
				copyLstProyecto.add(tmProyecto);
			}
		}
		
		perfil.setProyectos(copyLstProyecto);
		
		Set<Conocimiento> copyLstConocimiento = new HashSet<Conocimiento>();
		for(Conocimiento conocimiento: in.getConocimientos()) {
			try {
				conocimientoService.getConocimientoByNombre(conocimiento.getNombre());
				copyLstConocimiento.add(conocimiento);
			} catch (CustomNotFoundException e) {
				Conocimiento tmConocimiento = conocimientoService.nuevoConocimiento(conocimiento);
				copyLstConocimiento.add(tmConocimiento);
			}
		}
		
		perfil.setConocimientos(copyLstConocimiento);
		
		perfil.setIdioma(idiomaPerfilService.getIdiomaPerfilById(in.getId()));
		
		perfil.setEstado(in.getEstado());
	}

}
