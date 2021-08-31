package com.lmalvarez.services.perfil.conocimiento;

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
@RequestMapping(path = "api/int/v1/conocimiento")
public class ConocimientoController {
	@Autowired
	private ConocimientoService conocimientoService;
	
	@GetMapping
	public List<Conocimiento> getConocimientos() {
		return conocimientoService.getConocimientos();
	}

	@GetMapping(path = "{conocimientoId}")
	public Conocimiento getConocimientoById(@PathVariable("conocimientoId") Long conocimientoId) {
		return conocimientoService.getConocimientoById(conocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoConocimiento(@Valid @RequestBody Conocimiento conocimiento) {
		conocimientoService.nuevoConocimiento(conocimiento);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{conocimientoId}")
	public void eliminarConocimiento(@PathVariable("conocimientoId") Long conocimientoId) {
		conocimientoService.eliminarConocimiento(conocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarConocimiento(@Valid @RequestBody Conocimiento conocimiento) {
		conocimientoService.actualizarConocimiento(conocimiento);
	}

}
