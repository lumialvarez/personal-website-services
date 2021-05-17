package com.lmalvarez.services.perfil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	List<Perfil> findByEstado(Boolean b);
	

}
