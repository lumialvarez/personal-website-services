package com.lmalvarez.services.perfil.conocimiento;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.lmalvarez.services.perfil.categoriaConocimiento.CategoriaConocimiento;
import com.lmalvarez.services.perfil.tipoConocimiento.TipoConocimiento;

@Entity(name = "Conocimiento")
@Table(name = "conocimiento", uniqueConstraints = {
		@UniqueConstraint(name = "conocimiento_nombre_unique", columnNames = { "nombre" }) })
public class Conocimiento {
	@Id
	@SequenceGenerator(name = "conocimiento_sequence", sequenceName = "conocimiento_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conocimiento_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@NotBlank(message = "Campo nombre requerido")
	@Column(nullable = false)
	private String nombre;

	@Valid
	@ManyToOne(cascade = { CascadeType.DETACH }, optional = false)
	@JoinColumn(name = "categoria_conocimiento_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_conocimiento_a_categoria_conocimiento"))
	private TipoConocimiento tipo;

	@NotNull(message = "Campo nivel requerido")
	@Column(nullable = false)
	private Integer nivel;

	@Column(nullable = true)
	private String descripcion;

	@ManyToMany
	@JoinTable(name = "rel_categoria_conocimiento", 
		joinColumns = @JoinColumn(name = "conocimiento_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "categoria_conocimiento_id", referencedColumnName = "id"), 
		foreignKey = @ForeignKey(name = "FK_conocimiento_to_rel_categoria_conocimiento"), 
		inverseForeignKey = @ForeignKey(name = "FK_categoria_conocimiento_to_rel_categoria_conocimiento"))
	private Set<CategoriaConocimiento> categorias = new HashSet<>();

	public Conocimiento() {
	}

	public Conocimiento(@NotBlank(message = "Campo nombre requerido") String nombre,
			@NotNull(message = "Campo tipo requerido") TipoConocimiento tipo,
			@NotNull(message = "Campo nivel requerido") Integer nivel, String descripcion) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.descripcion = descripcion;
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

	public TipoConocimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoConocimiento tipo) {
		this.tipo = tipo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<CategoriaConocimiento> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaConocimiento> categorias) {
		this.categorias = categorias;
	}

}
