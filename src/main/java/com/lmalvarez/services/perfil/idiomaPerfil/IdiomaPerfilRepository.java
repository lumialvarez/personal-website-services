package com.lmalvarez.services.perfil.idiomaPerfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaPerfilRepository extends JpaRepository<IdiomaPerfil, Long> {
	

}
