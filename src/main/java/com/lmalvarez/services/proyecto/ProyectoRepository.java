package com.lmalvarez.services.proyecto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
	Optional<Proyecto> findByNombre(String nombre);

}
