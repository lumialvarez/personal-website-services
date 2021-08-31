package com.lmalvarez.services.perfil.proyecto;

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
@RequestMapping(path = "api/int/v1/proyecto")
public class ProyectoController {
	@Autowired
	private ProyectoService proyectoService;
	
	@GetMapping
	public List<Proyecto> getProyectos() {
		return proyectoService.getProyectos();
	}

	@GetMapping(path = "{proyectoId}")
	public Proyecto getProyectoById(@PathVariable("proyectoId") Long proyectoId) {
		return proyectoService.getProyectoById(proyectoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public void nuevoProyecto(@Valid @RequestBody Proyecto proyecto) {
		proyectoService.nuevoProyecto(proyecto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "{proyectoId}")
	public void eliminarProyecto(@PathVariable("proyectoId") Long proyectoId) {
		proyectoService.eliminarProyecto(proyectoId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public void actualizarProyecto(@Valid @RequestBody Proyecto proyecto) {
		proyectoService.actualizarProyecto(proyecto);
	}

}
