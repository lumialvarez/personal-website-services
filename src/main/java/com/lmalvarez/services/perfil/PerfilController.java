package com.lmalvarez.services.perfil;

import java.util.List;
import jakarta.validation.Valid;
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
@RequestMapping(path = "api/int/v1/perfil")
public class PerfilController {
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public List<Perfil> getPerfils() {
		return perfilService.getPerfil();
	}

	@GetMapping(path = "{tipoConocimientoId}")
	public Perfil getPerfilById(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		return perfilService.getPerfilById(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoPerfil(@Valid @RequestBody Perfil perfil) {
		perfilService.nuevoPerfil(perfil);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{tipoConocimientoId}")
	public void eliminarPerfil(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		perfilService.eliminarPerfil(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarPerfil(@Valid @RequestBody Perfil perfil) {
		perfilService.actualizarPerfil(perfil);
	}

}
