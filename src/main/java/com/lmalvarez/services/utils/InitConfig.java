package com.lmalvarez.services.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmalvarez.services.proyecto.Proyecto;
import com.lmalvarez.services.proyecto.ProyectoRepository;
import com.lmalvarez.services.security.rol.Rol;
import com.lmalvarez.services.security.rol.RolNombre;
import com.lmalvarez.services.security.rol.RolRepository;
import com.lmalvarez.services.security.usuario.Usuario;
import com.lmalvarez.services.security.usuario.UsuarioRepository;

@Configuration
public class InitConfig {
	@Bean
	CommandLineRunner commandLineRunnerStudent(ProyectoRepository proyectoRepository) {
		return args -> {
			Proyecto p = new Proyecto("Pagina WEB Personal", "Proyecto personal para crear una página donde puedo demostrar mis habilidades", "assets\\img\\proyectos\\personal_webpage\\principal.jpg");
			
			proyectoRepository.saveAll(List.of(p));
		};
	}
	
	@Bean
	CommandLineRunner commandLineRunnerRol(RolRepository rolRepository) {
		return args -> {
			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
	        Rol rolUser = new Rol(RolNombre.ROLE_USER);
			
	        rolRepository.saveAll(List.of(rolAdmin, rolUser));
		};
	}
	
	@Bean
	CommandLineRunner commandLineRunnerUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Usuario admin = new Usuario("admin", "admin", "admin@prueba.com", passwordEncoder.encode("admin"));
			admin.getRoles().add(new Rol(RolNombre.ROLE_ADMIN));
			
			Usuario user = new Usuario("admin", "user", "user@prueba.com", passwordEncoder.encode("user"));
			
			usuarioRepository.saveAll(List.of(admin, user));
		};
	}
}
