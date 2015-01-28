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
	private int anoEleccion;

	private int anos;

	private String posicion;

	private double salario;

	public Derecho() {
	}

	public DerechoPK getId() {
		return this.id;
	}

	public void setId(DerechoPK id) {
		this.id = id;
	}

	public int getAnoEleccion() {
		return this.anoEleccion;
	}

	public void setAnoEleccion(int anoEleccion) {
		this.anoEleccion = anoEleccion;
	}

	public int getAnos() {
		return this.anos;
	}

	public void setAnos(int anos) {
		this.anos = anos;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}