package com.lmalvarez.services.perfil.tipoConocimiento;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;

@Service
public class TipoConocimientoService {
	@Autowired
	private TipoConocimientoRepository tipoConocimientoRepository;

	public TipoConocimientoService() {
		super();
	}

	public List<TipoConocimiento> getTipoConocimientos() {
		return tipoConocimientoRepository.findAll();
	}

	public TipoConocimiento getTipoConocimientoById(Long id) {
		TipoConocimiento tipoConocimiento = tipoConocimientoRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Tipo de Conocimiento con id " + id + " no existe"));
		return tipoConocimiento;
	}

	public TipoConocimiento nuevoTipoConocimiento(TipoConocimiento tipoConocimiento) {
		return tipoConocimientoRepository.save(tipoConocimiento);
	}

	public void eliminarTipoConocimiento(Long id) {
		boolean exists = tipoConocimientoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Tipo de Conocimiento con id " + id + " no existe");
		}
		tipoConocimientoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarTipoConocimiento(TipoConocimiento in) {
		TipoConocimiento tipoConocimiento = getTipoConocimientoById(in.getId());

		tipoConocimiento.setNombre(in.getNombre());
	}

}
