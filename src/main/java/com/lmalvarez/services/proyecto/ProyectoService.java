package com.lmalvarez.services.proyecto;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;

@Service
public class ProyectoService {
	@Autowired
	private ProyectoRepository proyectoRepository;

	public ProyectoService() {
		super();
	}

	public List<Proyecto> getProyectos() {
		return proyectoRepository.findAll();
	}

	public Proyecto getProyectoById(Long id) {
		Proyecto proyecto = proyectoRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Proyecto con id " + id + " no existe"));
		return proyecto;
	}
	
	public Proyecto getProyectoByNombre(String nombre) {
		Proyecto proyecto = proyectoRepository.findByNombre(nombre)
				.orElseThrow(() -> new CustomNotFoundException("Proyecto " + nombre + " no existe"));
		return proyecto;
	}

	public Proyecto nuevoProyecto(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}

	public void eliminarProyecto(Long id) {
		boolean exists = proyectoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Proyecto con id " + id + " no existe");
		}
		proyectoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarProyecto(Proyecto in) {
		Proyecto proyecto = getProyectoById(in.getId());
		
		proyecto.setNombre(in.getNombre());
		
		proyecto.setDescripcion(in.getDescripcion());
		
		proyecto.setDetalle(in.getDetalle());

		proyecto.setImg(in.getImg());
	}

}
