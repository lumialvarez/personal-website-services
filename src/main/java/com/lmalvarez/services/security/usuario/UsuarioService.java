package com.lmalvarez.services.security.usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmalvarez.services.exception.CustomConflictException;
import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.security.dto.JwtDto;
import com.lmalvarez.services.security.dto.LoginUsuario;
import com.lmalvarez.services.security.dto.NuevoUsuario;
import com.lmalvarez.services.security.jwt.JwtProvider;
import com.lmalvarez.services.security.rol.Rol;
import com.lmalvarez.services.security.rol.RolNombre;
import com.lmalvarez.services.security.rol.RolService;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolService rolService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	public void crearUsuario(@Valid NuevoUsuario nuevoUsuario) {
		if (usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			throw new CustomConflictException("Nombre de Usuario " + nuevoUsuario.getNombreUsuario() + " ya existe");
		if (usuarioRepository.existsByEmail(nuevoUsuario.getEmail()))
			throw new CustomConflictException("Email " + nuevoUsuario.getEmail() + " ya existe");
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioRepository.save(usuario);

	}

	public JwtDto login(@Valid LoginUsuario loginUsuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return jwtDto;
	}

	public Usuario getByNombreUsuario(String nombreUsuario) {
		Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
				.orElseThrow(() -> new CustomNotFoundException("Usuario " + nombreUsuario + " no existe"));
		return usuario;
	}

	public Usuario getUsuarioById(int id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Usuario con id " + id + " no existe"));
		return usuario;
	}
}
