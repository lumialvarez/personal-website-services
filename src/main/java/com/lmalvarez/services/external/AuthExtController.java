package com.lmalvarez.services.external;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.security.dto.JwtDto;
import com.lmalvarez.services.security.dto.LoginUsuario;
import com.lmalvarez.services.security.usuario.UsuarioService;

@RestController
@RequestMapping("api/ext/v1/auth")
@CrossOrigin
public class AuthExtController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/login")
	public JwtDto login(@Valid @RequestBody LoginUsuario loginUsuario) {
		return usuarioService.login(loginUsuario);
	}
}
