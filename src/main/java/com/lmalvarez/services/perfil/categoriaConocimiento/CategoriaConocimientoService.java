package com.lmalvarez.services.perfil.categoriaConocimiento;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;

@Service
public class CategoriaConocimientoService {
	@Autowired
	private CategoriaConocimientoRepository categoriaConocimientoRepository;

	public CategoriaConocimientoService() {
		super();
	}

	public List<CategoriaConocimiento> getCategoriaConocimientos() {
		return categoriaConocimientoRepository.findAll();
	}

	public CategoriaConocimiento getCategoriaConocimientoById(Long id) {
		CategoriaConocimiento categoriaConocimiento = categoriaConocimientoRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Categoria de Conocimiento con id " + id + " no existe"));
		return categoriaConocimiento;
	}

	public void nuevoCategoriaConocimiento(CategoriaConocimiento categoriaConocimiento) {
		categoriaConocimientoRepository.save(categoriaConocimiento);
	}

	public void eliminarCategoriaConocimiento(Long id) {
		boolean exists = categoriaConocimientoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Categoria de Conocimiento con id " + id + " no existe");
		}
		categoriaConocimientoRepository.deleteById(id);
	}

	@Transactional
	public void actualizarCategoriaConocimiento(CategoriaConocimiento in) {
		CategoriaConocimiento categoriaConocimiento = getCategoriaConocimientoById(in.getId());

		categoriaConocimiento.setNombre(in.getNombre());
	}

}
