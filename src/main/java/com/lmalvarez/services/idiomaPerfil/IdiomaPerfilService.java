package com.lmalvarez.services.idiomaPerfil;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmalvarez.services.exception.CustomNotFoundException;

@Service
public class IdiomaPerfilService {
	@Autowired
	private IdiomaPerfilRepository idiomaPerfilRepository;

	public IdiomaPerfilService() {
		super();
	}

	public List<IdiomaPerfil> getIdiomaPerfil() {
		return idiomaPerfilRepository.findAll();
	}

	public IdiomaPerfil getIdiomaPerfilById(Long id) {
		IdiomaPerfil idiomaPerfil = idiomaPerfilRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Idioma de Perfil con id " + id + " no existe"));
		return idiomaPerfil;
	}

	public void nuevoIdiomaPerfil(IdiomaPerfil idiomaPerfil) {
		idiomaPerfilRepository.save(idiomaPerfil);
	}

	public void eliminarIdiomaPerfil(Long id) {
		boolean exists = idiomaPerfilRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Idioma de Perfil con id " + id + " no existe");
		}
		idiomaPerfilRepository.deleteById(id);
	}

	@Transactional
	public void actualizarIdiomaPerfil(IdiomaPerfil in) {
		IdiomaPerfil idiomaPerfil = getIdiomaPerfilById(in.getId());

		idiomaPerfil.setNombre(in.getNombre());
	}

}
