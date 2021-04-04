package com.lmalvarez.services.conocimiento;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.tipoConocimiento.TipoConocimiento;
import com.lmalvarez.services.tipoConocimiento.TipoConocimientoService;

@Service
public class ConocimientoService {
	@Autowired
	private ConocimientoRepository conocimientoRepository;
	
	@Autowired
	private TipoConocimientoService tipoConocimientoService;

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
	public void actualizarConocimiento(Conocimiento in) {
		Conocimiento conocimiento = getConocimientoById(in.getId());

		conocimiento.setNombre(in.getNombre());

		TipoConocimiento tipoConocimiento =  tipoConocimientoService.getTipoConocimientoById(in.getTipo().getId());
		conocimiento.setTipo(tipoConocimiento);
		
		conocimiento.setNivel(in.getNivel());

		conocimiento.setDescripcion(in.getDescripcion());
	}



}
