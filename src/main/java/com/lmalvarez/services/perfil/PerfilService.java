package com.lmalvarez.services.perfil;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.services.conocimiento.ConocimientoService;
import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.idiomaPerfil.IdiomaPerfilService;
import com.lmalvarez.services.proyecto.ProyectoService;

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
		
		in.getProyectos().forEach(proyecto -> proyectoService.getProyectoById(proyecto.getId()));
		
		in.getConocimientos().forEach(conocimiento -> conocimientoService.getConocimientoById(conocimiento.getId()));
		
		perfil.setIdioma(idiomaPerfilService.getIdiomaPerfilById(in.getId()));
		
		perfil.setEstado(in.getEstado());
	}

}
