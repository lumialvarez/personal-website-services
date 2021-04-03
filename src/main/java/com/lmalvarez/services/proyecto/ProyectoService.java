package com.lmalvarez.services.proyecto;

import java.util.List;
import java.util.Objects;
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

	public void nuevoProyecto(Proyecto proyecto) {
		proyectoRepository.save(proyecto);
	}

	public void eliminarProyecto(Long id) {
		boolean exists = proyectoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Proyecto con id " + id + " no existe");
		}
		proyectoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarProyecto(Long id, String nombre, String descripcion, String img) {
		Proyecto proyecto = getProyectoById(id);

		if (nombre != null && nombre.length() > 0 && !Objects.equals(proyecto.getNombre(), nombre)) {
			proyecto.setNombre(nombre);
		}

		if (descripcion != null && descripcion.length() > 0
				&& !Objects.equals(proyecto.getDescripcion(), descripcion)) {
			proyecto.setDescripcion(descripcion);
		}

		if (img != null && img.length() > 0 && !Objects.equals(proyecto.getImg(), img)) {
			proyecto.setImg(img);
		}

	}

}
