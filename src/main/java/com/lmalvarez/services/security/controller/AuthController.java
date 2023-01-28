package com.lmalvarez.services.security.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmalvarez.services.exception.CustomBadCredentialsException;
import com.lmalvarez.services.security.controller.dto.LoginResponse;
import com.lmalvarez.services.security.controller.dto.LoginUsuarioRequest;
import com.lmalvarez.services.security.controller.dto.NuevoUsuarioRequest;
import com.lmalvarez.services.security.usuario.Usuario;
import com.lmalvarez.services.security.usuario.UsuarioService;


@RestController
@RequestMapping("api/int/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    
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
    public void crearUsuario(@Valid @RequestBody NuevoUsuarioRequest nuevoUsuarioRequest){
    	usuarioService.crearUsuario(nuevoUsuarioRequest);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginUsuarioRequest loginUsuarioRequest, HttpServletRequest request) throws CustomBadCredentialsException{
    	String ipAddress = request.getRemoteAddr();
    	return usuarioService.login(loginUsuarioRequest, ipAddress);
    }
    
    @GetMapping("/current")
    public Usuario checkCurrentUser() throws CustomBadCredentialsException{
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = ((UserDetails)auth.getPrincipal()).getUsername();
    	return usuarioService.getByNombreUsuario(username);
    }
}
