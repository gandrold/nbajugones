package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the derechos database table.
 *
 */
@Entity
@Table(name="derechos")
public class Derecho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	@Column(name="id_equipo")
	private String idEquipo;

	@Column(name="jugador")
	private String jugador;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
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