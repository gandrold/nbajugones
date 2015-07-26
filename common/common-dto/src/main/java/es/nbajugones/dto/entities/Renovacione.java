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
	private Integer years;

	@Column(name="ID_EQUIPO_GANADOR")
	private String idEquipoGanador;

	@Column(name="ID_EQUIPO_PROP")
	private String idEquipoProp;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_JUGADOR",insertable=false, updatable=false)
	private Jugadores jugador;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EQUIPO_GANADOR",insertable=false, updatable=false)
	private Equipo equipoGanador;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EQUIPO_PROP",insertable=false, updatable=false)
	private Equipo equipoPropietario;

	private String posicion;

	private Double promedio;

	private Double puntos;

	private String renueva;

	private Double salario;

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

	public Double getPromedio() {
		return this.promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}

	public Double getPuntos() {
		return this.puntos;
	}

	public void setPuntos(Double puntos) {
		this.puntos = puntos;
	}

	public String getRenueva() {
		return this.renueva;
	}

	public void setRenueva(String renueva) {
		this.renueva = renueva;
	}

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public int getTanda() {
		return this.tanda;
	}

	public void setTanda(int tanda) {
		this.tanda = tanda;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Jugadores getJugador() {
		return jugador;
	}

	public void setJugador(Jugadores jugador) {
		this.jugador = jugador;
	}

	public Equipo getEquipoGanador() {
		return equipoGanador;
	}

	public void setEquipoGanador(Equipo equipoGanador) {
		this.equipoGanador = equipoGanador;
	}

	public Equipo getEquipoPropietario() {
		return equipoPropietario;
	}

	public void setEquipoPropietario(Equipo equipoPropietario) {
		this.equipoPropietario = equipoPropietario;
	}

	
	
}