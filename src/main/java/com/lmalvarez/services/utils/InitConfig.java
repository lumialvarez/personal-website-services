package com.lmalvarez.services.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmalvarez.services.categoriaConocimiento.CategoriaConocimiento;
import com.lmalvarez.services.categoriaConocimiento.CategoriaConocimientoRepository;
import com.lmalvarez.services.conocimiento.Conocimiento;
import com.lmalvarez.services.conocimiento.ConocimientoRepository;
import com.lmalvarez.services.idiomaPerfil.IdiomaPerfil;
import com.lmalvarez.services.idiomaPerfil.IdiomaPerfilRepository;
import com.lmalvarez.services.perfil.Perfil;
import com.lmalvarez.services.perfil.PerfilRepository;
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
	CommandLineRunner commandLineRunner(ProyectoRepository proyectoRepository, ConocimientoRepository conocimientoRepository, TipoConocimientoRepository tipoConocimientoRepository, CategoriaConocimientoRepository categoriaConocimientoRepository, IdiomaPerfilRepository idiomaPerfilRepository, PerfilRepository perfilRepository) {
		return args -> {
			Proyecto proyecto = new Proyecto("Pagina WEB Personal", "Proyecto personal para crear una página donde puedo demostrar mis habilidades", "assets\\img\\proyectos\\personal_webpage\\principal.jpg");
			List<Proyecto> lstProyectos = proyectoRepository.saveAll(List.of(proyecto));
			
			TipoConocimiento tcLenguaje =tipoConocimientoRepository.save(new TipoConocimiento("Lenguaje"));
			TipoConocimiento tcFramework =tipoConocimientoRepository.save(new TipoConocimiento("Framework"));
			TipoConocimiento tcHerramienta =tipoConocimientoRepository.save(new TipoConocimiento("Herramienta"));
			TipoConocimiento tcOtros =tipoConocimientoRepository.save(new TipoConocimiento("Otros"));
			
			CategoriaConocimiento ccFullstack = categoriaConocimientoRepository.save(new CategoriaConocimiento("Fullstack"));
			CategoriaConocimiento ccBackend = categoriaConocimientoRepository.save(new CategoriaConocimiento("Backend"));
			CategoriaConocimiento ccIntegracion = categoriaConocimientoRepository.save(new CategoriaConocimiento("Integracion"));
			CategoriaConocimiento ccFrontend = categoriaConocimientoRepository.save(new CategoriaConocimiento("Frontend"));
			categoriaConocimientoRepository.save(new CategoriaConocimiento("Infraestructura"));
			categoriaConocimientoRepository.save(new CategoriaConocimiento("Base de datos"));
			categoriaConocimientoRepository.save(new CategoriaConocimiento("Devops"));
			
			Conocimiento java = new Conocimiento("Java", tcLenguaje, 90, "");
			java.setCategorias(Set.of(ccFullstack, ccBackend, ccIntegracion));
			Conocimiento javascript = new Conocimiento("Javascript", tcLenguaje, 80, "");
			javascript.setCategorias(Set.of(ccFullstack, ccBackend, ccIntegracion, ccFrontend));
			List<Conocimiento> lstConocimientos = conocimientoRepository.saveAll(List.of(java, javascript));
			
			IdiomaPerfil idiomaPerfil = idiomaPerfilRepository.save(new IdiomaPerfil("Español"));
			
			
			Perfil perfil = new Perfil();
			perfil.setNombre("Luis Miguel Alvarez");
			perfil.setProfesion("Ingeniero de Desarrollo");
			perfil.setIdioma(idiomaPerfil);
			perfil.setProyectos(new HashSet<>(lstProyectos));
			perfil.setConocimientos(new HashSet<>(lstConocimientos));
			perfil.setEstado(true);
			
			perfilRepository.save(perfil);
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
