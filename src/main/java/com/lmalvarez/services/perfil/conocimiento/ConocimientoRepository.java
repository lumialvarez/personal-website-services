package com.lmalvarez.services.perfil.conocimiento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConocimientoRepository extends JpaRepository<Conocimiento, Long> {
	Optional<Conocimiento> findByNombre(String nombre);

}
