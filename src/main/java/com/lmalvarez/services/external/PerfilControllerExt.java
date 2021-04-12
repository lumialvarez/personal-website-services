package com.lmalvarez.services.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.perfil.Perfil;
import com.lmalvarez.services.perfil.PerfilService;

@RestController
@RequestMapping(path = "api/ext/v1/perfil")
public class PerfilControllerExt {
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public List<Perfil> getPerfil() {
		return perfilService.getPerfilActivo();
	}

}
