package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.DerechoPK;


/**
 * The persistent class for the derechos database table.
 * 
 */
@Entity
@Table(name="derechos")
public class Derecho implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DerechoPK id;

	@Column(name="ano_eleccion")
	private Integer anoEleccion;

	@Column(name="anos")
	private Integer anos;

	@Column(name="posicion")
	private String posicion;

	@Column(name="salario")
	private Double salario;

	public Derecho() {
	}

	public DerechoPK getId() {
		return this.id;
	}

	public void setId(DerechoPK id) {
		this.id = id;
	}

	public Integer getAnoEleccion() {
		return this.anoEleccion;
	}

	public void setAnoEleccion(Integer anoEleccion) {
		this.anoEleccion = anoEleccion;
	}

	public Integer getAnos() {
		return this.anos;
	}

	public void setAnos(Integer anos) {
		this.anos = anos;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}