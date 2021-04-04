package com.lmalvarez.services.conocimiento;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lmalvarez.services.tipoConocimiento.TipoConocimiento;

@Entity(name = "Conocimiento")
@Table(name = "conocimiento")
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
	@JoinColumn(name = "tipo_conocimiento_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_conocimiento_a_tipo_conocimiento"))
	private TipoConocimiento tipo;

	@NotNull(message = "Campo nivel requerido")
	@Column(nullable = false)
	private Integer nivel;

	@Column(nullable = true)
	private String descripcion;

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

}
