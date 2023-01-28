package com.lmalvarez.services.security.usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lmalvarez.services.exception.CustomBadCredentialsException;
import com.lmalvarez.services.exception.CustomConflictException;
import com.lmalvarez.services.exception.CustomNotFoundException;
import com.lmalvarez.services.notificacion.NotificacionService;
import com.lmalvarez.services.security.controller.dto.LoginResponse;
import com.lmalvarez.services.security.controller.dto.LoginUsuarioRequest;
import com.lmalvarez.services.security.controller.dto.NuevoUsuarioRequest;
import com.lmalvarez.services.security.config.JwtProvider;
import com.lmalvarez.services.security.rol.Rol;
import com.lmalvarez.services.security.rol.RolNombre;
import com.lmalvarez.services.security.rol.RolService;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	private final RolService rolService;

	private final AuthenticationManager authenticationManager;

	private final JwtProvider jwtProvider;

	private final PasswordEncoder passwordEncoder;

	private final NotificacionService notificacionService;
	
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	public void crearUsuario(@Valid NuevoUsuarioRequest nuevoUsuarioRequest) {
		if (usuarioRepository.existsByNombreUsuario(nuevoUsuarioRequest.getNombreUsuario()))
			throw new CustomConflictException("Nombre de Usuario " + nuevoUsuarioRequest.getNombreUsuario() + " ya existe");
		if (usuarioRepository.existsByEmail(nuevoUsuarioRequest.getEmail()))
			throw new CustomConflictException("Email " + nuevoUsuarioRequest.getEmail() + " ya existe");
		Usuario usuario = new Usuario(nuevoUsuarioRequest.getNombre(), nuevoUsuarioRequest.getNombreUsuario(),
				nuevoUsuarioRequest.getEmail(), passwordEncoder.encode(nuevoUsuarioRequest.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		if (nuevoUsuarioRequest.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioRepository.save(usuario);

	}

	public LoginResponse login(@Valid LoginUsuarioRequest loginUsuarioRequest, String ipAddress) throws CustomBadCredentialsException {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUsuarioRequest.getNombreUsuario(), loginUsuarioRequest.getPassword()));
		} catch (BadCredentialsException badCredentialsException) {
			createNotificacionLoginFallido(loginUsuarioRequest.getNombreUsuario(), loginUsuarioRequest.getPassword(), ipAddress);
			throw new CustomBadCredentialsException("Usuario o password invalido", badCredentialsException);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		LoginResponse loginResponse = new LoginResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return loginResponse;
	}
	
	public void validateToken(String token) throws CustomBadCredentialsException {
		boolean isValid = jwtProvider.validateToken(token);
		if(!isValid) {
			throw new CustomBadCredentialsException("Token invalido");
		}
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
	
	private void createNotificacionLoginFallido(String usuario, String password, String ipAddress) {
		String fecha = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now());
		notificacionService.nuevaNotificacion("Intento de logueo fallido Fecha:" + fecha + " User:" + usuario + " Pass:" + password + " Ip:" + ipAddress);
	}
	
}
