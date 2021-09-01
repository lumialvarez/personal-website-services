package com.lmalvarez.services.notificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

	@Query("select n from Notificacion n inner join n.usuarioNotificaciones un where un.read = :read")
	List<Notificacion> findByReadStatus(boolean read);

	@Query("select n from Notificacion n inner join n.usuarioNotificaciones un  inner join un.usuario unu "
			+ "where un.read = :read and unu.id = :usuarioId")
	List<Notificacion> findByReadStatusAndUsuario(boolean read, int usuarioId);

}
