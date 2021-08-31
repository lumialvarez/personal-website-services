package com.lmalvarez.services.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.perfil.categoriaConocimiento.CategoriaConocimiento;
import com.lmalvarez.services.perfil.categoriaConocimiento.CategoriaConocimientoService;

@RestController
@RequestMapping(path = "api/ext/v1/categoria_conocimiento")
public class CategoriaConocimientoControllerExt {
	@Autowired
	private CategoriaConocimientoService categoriaConocimientoService;

	@GetMapping
	public List<CategoriaConocimiento> getCategoriaConocimientos() {
		return categoriaConocimientoService.getCategoriaConocimientos();
	}

}
