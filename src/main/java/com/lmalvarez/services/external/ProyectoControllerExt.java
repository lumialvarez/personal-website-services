package com.lmalvarez.services.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.perfil.proyecto.Proyecto;
import com.lmalvarez.services.perfil.proyecto.ProyectoService;

@RestController
@RequestMapping(path = "api/ext/v1/proyecto")
public class ProyectoControllerExt {
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
}
