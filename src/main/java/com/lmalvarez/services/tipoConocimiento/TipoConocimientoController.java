package com.lmalvarez.services.tipoConocimiento;

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
@RequestMapping(path = "api/int/v1/tipo_conocimiento")
public class TipoConocimientoController {
	@Autowired
	private TipoConocimientoService tipoConocimientoService;
	
	@GetMapping
	public List<TipoConocimiento> getTipoConocimientos() {
		return tipoConocimientoService.getTipoConocimientos();
	}

	@GetMapping(path = "{tipoConocimientoId}")
	public TipoConocimiento getTipoConocimientoById(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		return tipoConocimientoService.getTipoConocimientoById(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoTipoConocimiento(@Valid @RequestBody TipoConocimiento tipoConocimiento) {
		tipoConocimientoService.nuevoTipoConocimiento(tipoConocimiento);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{tipoConocimientoId}")
	public void eliminarTipoConocimiento(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		tipoConocimientoService.eliminarTipoConocimiento(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarTipoConocimiento(@Valid @RequestBody TipoConocimiento tipoConocimiento) {
		tipoConocimientoService.actualizarTipoConocimiento(tipoConocimiento);
	}

}
