package com.lmalvarez.services.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.security.dto.JwtDto;
import com.lmalvarez.services.security.dto.LoginUsuario;
import com.lmalvarez.services.security.dto.NuevoUsuario;
import com.lmalvarez.services.security.usuario.Usuario;
import com.lmalvarez.services.security.usuario.UsuarioService;


@RestController
@RequestMapping("api/int/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public List<Usuario> getUsuarios(){
    	return usuarioService.getUsuarios();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userIdentificador}")
    public Usuario getUsuarioById(@PathVariable("userIdentificador") String userIdentificador){
    	try {
    		int id = Integer.parseInt(userIdentificador);
    		return usuarioService.getUsuarioById(id);
    	} catch(NumberFormatException e) {
    		return usuarioService.getByNombreUsuario(userIdentificador);
    	}
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user")
    public void crearUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario){
    	usuarioService.crearUsuario(nuevoUsuario);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/login")
    public JwtDto login(@Valid @RequestBody LoginUsuario loginUsuario){
    	return usuarioService.login(loginUsuario);
    }
}
