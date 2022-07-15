package es.nbajugones.dto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the jugadores database table.
 *
 */

@NamedQueries({@NamedQuery(name = "Jugadores.getPlantilla",
query = "SELECT j FROM Jugadores j WHERE j.idjugador IN (:plantilla)") })

@Entity
@Table(name="jugadores")


public class Jugadores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID_JUGADOR")
	private Integer idjugador;

	@Column(name="ACTIVO")
	private Integer activo;

	@Column(name="AÑOS")
	private String years;

	@Column(name="CORTADO_POR")
	private String cortadopor;

	@Column(name="JUGADOR")
	private String jugador;

	@Column(name="JUGADOS")
	private Integer jugados;

	@Column(name="MINUTOS")
	private Double minutos;

	@Column(name="POSICION")
	private String posicion;

	@Column(name="PROMEDIO")
	private Double promedio;

	@Column(name="PUNTOS")
	private Double puntos;

	@Column(name="SALARIO")
	private Double salario;

	@Column(name="player_id")
	private Integer playerid;

	@Column(name="renovar")
	private Integer renovar;

	@Column(name="lesionado")
	private Integer lesionado;

	@Column(name="status")
	private Integer status;

	@Column(name = "equipo_real")
	private String equiporeal;

    @Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;



	@Transient
	private String equipo;

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Jugadores() {
	}

	public Integer getIdjugador() {
		return this.idjugador;
	}

	public void setIdjugador(Integer idjugador) {
		this.idjugador = idjugador;
	}

	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}



	public String getCortadopor() {
		return this.cortadopor;
	}

	public void setCortadopor(String cortadopor) {
		this.cortadopor = cortadopor;
	}

	public String getJugador() {
		return this.jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public Integer getJugados() {
		return this.jugados;
	}

	public void setJugados(Integer jugados) {
		this.jugados = jugados;
	}

	public Double getMinutos() {
		return this.minutos;
	}

	public void setMinutos(Double minutos) {
		this.minutos = minutos;
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

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public Integer getPlayerid() {
		return playerid;
	}

	public void setPlayerid(Integer playerid) {
		this.playerid = playerid;
	}

	public Integer getRenovar() {
		return renovar;
	}

	public void setRenovar(Integer renovar) {
		this.renovar = renovar;
	}

	public Integer getLesionado() {
		return lesionado;
	}

	public void setLesionado(Integer lesionado) {
		this.lesionado = lesionado;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEquiporeal() {
		return equiporeal;
	}

	public void setEquiporeal(String equiporeal) {
		this.equiporeal = equiporeal;
	}

	@Override
	public String toString() {
		return "Jugadores{" +
				"idJugador=" + idjugador +
				", activo=" + activo +
				", years='" + years + '\'' +
				", cortadoPor='" + cortadopor + '\'' +
				", jugador='" + jugador + '\'' +
				", jugados=" + jugados +
				", minutos=" + minutos +
				", posicion='" + posicion + '\'' +
				", promedio=" + promedio +
				", puntos=" + puntos +
				", salario=" + salario +
				", playerId=" + playerid +
				", renovar=" + renovar +
				", lesionado=" + lesionado +
				", status=" + status +
				", fecha=" + fecha +
				", equipo='" + equipo + '\'' +
				'}';
	}
}