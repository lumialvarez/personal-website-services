package com.lmalvarez.services.tipoConocimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoConocimientoRepository extends JpaRepository<TipoConocimiento, Long> {
	

}
