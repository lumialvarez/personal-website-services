package com.lmalvarez.services.conocimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConocimientoRepository extends JpaRepository<Conocimiento, Long> {
	

}
