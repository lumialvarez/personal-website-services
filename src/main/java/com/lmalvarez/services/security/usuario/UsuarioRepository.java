package com.lmalvarez.services.security.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lmalvarez.services.security.rol.RolNombre;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    
    @Query( "select u from User u  where u.roles in :roles" )
    List<Usuario> findByRol(RolNombre roleAdmin);

}
