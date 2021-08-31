package com.lmalvarez.services.notificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioNotificacionRepository extends JpaRepository<UsuarioNotificacion, Long> {

	List<UsuarioNotificacion> findByLecturaFalse();

}
