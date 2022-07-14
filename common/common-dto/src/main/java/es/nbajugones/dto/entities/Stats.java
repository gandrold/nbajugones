/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import es.nbajugones.dto.entities.pk.StatsPK;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "stats")
@NamedQueries({
	@NamedQuery(name = "Stats.findAll", query = "SELECT s FROM Stats s"),
	@NamedQuery(name = "Stats.findBySeason", query = "SELECT s FROM Stats s JOIN s.schedule sc WHERE s.statsPK.idJugador = :idJugador "
			+ "and sc.season = :season and type= :type order by sc.startdate desc")})
public class Stats implements Serializable {
	@JoinColumn(name = "id_jugador", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Players players;
	@JoinColumn(name = "id_partido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Schedule schedule;
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected StatsPK statsPK;
	@Basic(optional = false)
	@Column(name = "minutos")
	private Integer minutos;
	@Column(name = "puntos")
	private Integer puntos;
	@Column(name = "asistencias")
	private Integer asistencias;
	@Column(name = "perdidas")
	private Integer perdidas;
	@Column(name = "tapones")
	private Integer tapones;
	@Column(name = "fga")
	private Integer fga;
	@Column(name = "fgm")
	private Integer fgm;
	@Column(name = "3pa")
	private Integer tpa;
	@Column(name = "3pm")
	private Integer tpm;
	@Column(name = "fta")
	private Integer fta;
	@Column(name = "ftm")
	private Integer ftm;
	@Column(name = "rebDef")
	private Integer rebDef;
	@Column(name = "rebOf")
	private Integer rebOf;
	@Column(name = "faltas")
	private Integer faltas;
	@Column(name = "robos")
	private Integer robos;
	@Column(name = "starter")
	private Integer starter;
	@Transient
	private int id_jugador;
	@Transient
	private String id_equipo;
	@Transient
	private int id_partido;


	public Stats() {
	}

	public Stats(StatsPK statsPK) {
		this.statsPK = statsPK;
	}

	public Stats(int idJugador, String idEquipo, int jornada) {
		this.statsPK = new StatsPK(idJugador, idEquipo, jornada);
	}

	public StatsPK getStatsPK() {
		return statsPK;
	}

	public void setStatsPK(StatsPK statsPK) {
		this.statsPK = statsPK;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(Integer asistencias) {
		this.asistencias = asistencias;
	}

	public Integer getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(Integer perdidas) {
		this.perdidas = perdidas;
	}

	public Integer getTapones() {
		return tapones;
	}

	public void setTapones(Integer tapones) {
		this.tapones = tapones;
	}

	public Integer getFga() {
		return fga;
	}

	public void setFga(Integer fga) {
		this.fga = fga;
	}

	public Integer getFgm() {
		return fgm;
	}

	public void setFgm(Integer fgm) {
		this.fgm = fgm;
	}

	public Integer getTpa() {
		return tpa;
	}

	public void setTpa(Integer pa) {
		this.tpa = pa;
	}

	public Integer getTpm() {
		return tpm;
	}

	public void setTpm(Integer pm) {
		this.tpm = pm;
	}

	public Integer getFta() {
		return fta;
	}

	public void setFta(Integer fta) {
		this.fta = fta;
	}

	public Integer getFtm() {
		return ftm;
	}

	public void setFtm(Integer ftm) {
		this.ftm = ftm;
	}

	public Integer getRebDef() {
		return rebDef;
	}

	public void setRebDef(Integer rebDef) {
		this.rebDef = rebDef;
	}

	public Integer getRebOf() {
		return rebOf;
	}

	public void setRebOf(Integer rebOf) {
		this.rebOf = rebOf;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	public Integer getStarter() {
		return starter;
	}

	public void setStarter(Integer starter) {
		this.starter = starter;
	}

	public Integer getRobos() {
		return robos;
	}

	public void setRobos(Integer robos) {
		this.robos = robos;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}

	public String getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(String id_equipo) {
		this.id_equipo = id_equipo;
	}

	public int getId_partido() {
		return id_partido;
	}

	public void setId_partido(int id_partido) {
		this.id_partido = id_partido;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (statsPK != null ? statsPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Stats)) {
			return false;
		}
		Stats other = (Stats) object;
		if ((this.statsPK == null && other.statsPK != null) || (this.statsPK != null && !this.statsPK.equals(other.statsPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "es.nbajugones.dto.entities.Stats[ statsPK=" + statsPK + " ]";
	}

	public Players getPlayers() {
		return players;
	}

	public void setPlayers(Players players) {
		this.players = players;
	}



}
