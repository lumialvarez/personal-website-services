package com.lmalvarez.services.external;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.exception.CustomBadCredentialsException;
import com.lmalvarez.services.security.controller.dto.LoginResponse;
import com.lmalvarez.services.security.controller.dto.LoginUsuarioRequest;
import com.lmalvarez.services.security.usuario.UsuarioService;

@RestController
@RequestMapping("api/ext/v1/auth")
@CrossOrigin
public class AuthControllerExt {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginUsuarioRequest loginUsuarioRequest, HttpServletRequest request) throws CustomBadCredentialsException {
		String ipAddress = request.getRemoteAddr();
    	return usuarioService.login(loginUsuarioRequest, ipAddress);
	}
}
