package com.lmalvarez.services.perfil.categoriaConocimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaConocimientoRepository extends JpaRepository<CategoriaConocimiento, Long> {
	

}
