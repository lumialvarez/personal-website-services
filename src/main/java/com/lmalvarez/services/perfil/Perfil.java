package com.lmalvarez.services.perfil;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lmalvarez.services.perfil.conocimiento.Conocimiento;
import com.lmalvarez.services.perfil.idiomaPerfil.IdiomaPerfil;
import com.lmalvarez.services.perfil.proyecto.Proyecto;

@Entity(name = "Perfil")
@Table(name = "perfil")
public class Perfil {
	@Id
	@SequenceGenerator(name = "perfil_sequence", sequenceName = "perfil_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Campo nombre requerido")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@NotBlank(message = "Campo profesion requerido")
	@Column(name = "profesion", nullable = false)
	private String profesion;
	
	@Column(name = "perfil_profesional", columnDefinition = "TEXT")
	private String perfilProfesional;
	
	@Column(name = "perfil_personal", columnDefinition = "TEXT")
	private String perfilPersonal;
	
	@OneToMany(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "perfil_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_perfil_a_proyecto"))
    private Set<Proyecto> proyectos = new HashSet<>();
	
	@OneToMany(cascade = { CascadeType.DETACH })
    @JoinColumn(name = "perfil_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_perfil_a_conocimiento"))
    private Set<Conocimiento> conocimientos = new HashSet<>();
	
	@Valid
	@ManyToOne(cascade = { CascadeType.DETACH }, optional = false)
	@JoinColumn(name = "idioma_perfil_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_perfil_a_idioma_perfil"))
	private IdiomaPerfil idioma;
	
	@NotNull(message = "Campo estado requerido")
	@Column(name = "estado", nullable = false)
	private Boolean estado;

	public Perfil() {
	}

	public Perfil(@NotBlank(message = "Campo nombre requerido") String nombre,
			@NotBlank(message = "Campo profesion requerido") String profesion, String perfilProfesional,
			String perfilPersonal, @Valid IdiomaPerfil idioma,
			@NotBlank(message = "Campo estado requerido") Boolean estado) {
		super();
		this.nombre = nombre;
		this.profesion = profesion;
		this.perfilProfesional = perfilProfesional;
		this.perfilPersonal = perfilPersonal;
		this.idioma = idioma;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getPerfilProfesional() {
		return perfilProfesional;
	}

	public void setPerfilProfesional(String perfilProfesional) {
		this.perfilProfesional = perfilProfesional;
	}

	public String getPerfilPersonal() {
		return perfilPersonal;
	}

	public void setPerfilPersonal(String perfilPersonal) {
		this.perfilPersonal = perfilPersonal;
	}

	public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Set<Conocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(Set<Conocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	public IdiomaPerfil getIdioma() {
		return idioma;
	}

	public void setIdioma(IdiomaPerfil idioma) {
		this.idioma = idioma;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
