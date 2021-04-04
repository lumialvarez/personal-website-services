package com.lmalvarez.services.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmalvarez.services.conocimiento.Conocimiento;
import com.lmalvarez.services.conocimiento.ConocimientoRepository;
import com.lmalvarez.services.proyecto.Proyecto;
import com.lmalvarez.services.proyecto.ProyectoRepository;
import com.lmalvarez.services.security.rol.Rol;
import com.lmalvarez.services.security.rol.RolNombre;
import com.lmalvarez.services.security.rol.RolRepository;
import com.lmalvarez.services.security.usuario.Usuario;
import com.lmalvarez.services.security.usuario.UsuarioRepository;
import com.lmalvarez.services.tipoConocimiento.TipoConocimiento;
import com.lmalvarez.services.tipoConocimiento.TipoConocimientoRepository;

@Configuration
public class InitConfig {
	@Bean
	CommandLineRunner commandLineRunner(ProyectoRepository proyectoRepository, ConocimientoRepository conocimientoRepository, TipoConocimientoRepository tipoConocimientoRepository) {
		return args -> {
			Proyecto p = new Proyecto("Pagina WEB Personal", "Proyecto personal para crear una página donde puedo demostrar mis habilidades", "assets\\img\\proyectos\\personal_webpage\\principal.jpg");
			proyectoRepository.saveAll(List.of(p));
			
			TipoConocimiento tcLenguaje =tipoConocimientoRepository.save(new TipoConocimiento("Lenguaje"));
			TipoConocimiento tcFramework =tipoConocimientoRepository.save(new TipoConocimiento("Framework"));
			TipoConocimiento tcHerramienta =tipoConocimientoRepository.save(new TipoConocimiento("Herramienta"));
			TipoConocimiento tcOtros =tipoConocimientoRepository.save(new TipoConocimiento("Otros"));
			
			Conocimiento c = new Conocimiento("Java", tcLenguaje, 90, "");
			conocimientoRepository.saveAll(List.of(c));
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
