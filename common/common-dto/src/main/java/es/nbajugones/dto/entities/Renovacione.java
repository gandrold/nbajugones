package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.RenovacionePK;


/**
 * The persistent class for the renovaciones database table.
 * 
 */
@Entity
@Table(name="renovaciones")
public class Renovacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RenovacionePK id;

	@Column(name="AÑOS")
	private int years;

	@Column(name="ID_EQUIPO_GANADOR")
	private String idEquipoGanador;

	@Column(name="ID_EQUIPO_PROP")
	private String idEquipoProp;

	private String posicion;

	private double promedio;

	private double puntos;

	private String renueva;

	private double salario;

	private int tanda;

	public Renovacione() {
	}

	public RenovacionePK getId() {
		return this.id;
	}

	public void setId(RenovacionePK id) {
		this.id = id;
	}


	public String getIdEquipoGanador() {
		return this.idEquipoGanador;
	}

	public void setIdEquipoGanador(String idEquipoGanador) {
		this.idEquipoGanador = idEquipoGanador;
	}

	public String getIdEquipoProp() {
		return this.idEquipoProp;
	}

	public void setIdEquipoProp(String idEquipoProp) {
		this.idEquipoProp = idEquipoProp;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public double getPromedio() {
		return this.promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public double getPuntos() {
		return this.puntos;
	}

	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public String getRenueva() {
		return this.renueva;
	}

	public void setRenueva(String renueva) {
		this.renueva = renueva;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getTanda() {
		return this.tanda;
	}

	public void setTanda(int tanda) {
		this.tanda = tanda;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

}