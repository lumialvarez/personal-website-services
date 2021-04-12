package com.lmalvarez.services.idiomaPerfil;

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
@RequestMapping(path = "api/int/v1/idioma_perfil")
public class IdiomaPerfilController {
	@Autowired
	private IdiomaPerfilService idiomaPerfilService;
	
	@GetMapping
	public List<IdiomaPerfil> getIdiomaPerfils() {
		return idiomaPerfilService.getIdiomaPerfil();
	}

	@GetMapping(path = "{tipoConocimientoId}")
	public IdiomaPerfil getIdiomaPerfilById(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		return idiomaPerfilService.getIdiomaPerfilById(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoIdiomaPerfil(@Valid @RequestBody IdiomaPerfil idiomaPerfil) {
		idiomaPerfilService.nuevoIdiomaPerfil(idiomaPerfil);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{tipoConocimientoId}")
	public void eliminarIdiomaPerfil(@PathVariable("tipoConocimientoId") Long tipoConocimientoId) {
		idiomaPerfilService.eliminarIdiomaPerfil(tipoConocimientoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarIdiomaPerfil(@Valid @RequestBody IdiomaPerfil idiomaPerfil) {
		idiomaPerfilService.actualizarIdiomaPerfil(idiomaPerfil);
	}

}
