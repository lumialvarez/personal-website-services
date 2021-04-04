package com.lmalvarez.services.categoriaConocimiento;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/int/v1/categoria_conocimiento")
public class CategoriaConocimientoController {
	@Autowired
	private CategoriaConocimientoService categoriaConocimientoService;
	
	@GetMapping
	public List<CategoriaConocimiento> getCategoriaConocimientos() {
		return categoriaConocimientoService.getCategoriaConocimientos();
	}

	@GetMapping(path = "{categoriaConocimientoId}")
	public CategoriaConocimiento getCategoriaConocimientoById(@PathVariable("categoriaConocimientoId") Long categoriaConocimientoId) {
		return categoriaConocimientoService.getCategoriaConocimientoById(categoriaConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoCategoriaConocimiento(@Valid @RequestBody CategoriaConocimiento categoriaConocimiento) {
		categoriaConocimientoService.nuevoCategoriaConocimiento(categoriaConocimiento);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{categoriaConocimientoId}")
	public void eliminarCategoriaConocimiento(@PathVariable("categoriaConocimientoId") Long categoriaConocimientoId) {
		categoriaConocimientoService.eliminarCategoriaConocimiento(categoriaConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarCategoriaConocimiento(@Valid @RequestBody CategoriaConocimiento categoriaConocimiento) {
		categoriaConocimientoService.actualizarCategoriaConocimiento(categoriaConocimiento);
	}

}
