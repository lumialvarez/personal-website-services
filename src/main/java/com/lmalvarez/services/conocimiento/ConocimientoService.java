package com.lmalvarez.services.conocimiento;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;

@Service
public class ConocimientoService {
	@Autowired
	private ConocimientoRepository conocimientoRepository;

	public ConocimientoService() {
		super();
	}

	public List<Conocimiento> getConocimientos() {
		return conocimientoRepository.findAll();
	}

	public Conocimiento getConocimientoById(Long id) {
		Conocimiento conocimiento = conocimientoRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Conocimiento con id " + id + " no existe"));
		return conocimiento;
	}

	public void nuevoConocimiento(Conocimiento conocimiento) {
		conocimientoRepository.save(conocimiento);
	}

	public void eliminarConocimiento(Long id) {
		boolean exists = conocimientoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Conocimiento con id " + id + " no existe");
		}
		conocimientoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarConocimiento(Long id, String nombre, String tipo, Integer nivel, String descripcion) {
		Conocimiento conocimiento = getConocimientoById(id);

		if (nombre != null && nombre.length() > 0 && !Objects.equals(conocimiento.getNombre(), nombre)) {
			conocimiento.setNombre(nombre);
		}
		
		if (tipo != null && tipo.length() > 0
				&& !Objects.equals(conocimiento.getTipo(), tipo)) {
			conocimiento.setTipo(tipo);
		}
		
		if (nivel != null && !Objects.equals(conocimiento.getNivel(), nivel)) {
			conocimiento.setNivel(nivel);
		}

		if (descripcion != null && descripcion.length() > 0
				&& !Objects.equals(conocimiento.getDescripcion(), descripcion)) {
			conocimiento.setDescripcion(descripcion);
		}
	}



}
