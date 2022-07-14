package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.CalendarioLigaPK;

import java.util.Date;


/**
 * The persistent class for the calendario_liga database table.
 * 
 */
@Entity
@Table(name="calendario_liga")
public class CalendarioLiga implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CalendarioLigaPK id;

	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String url;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo_casa", insertable=false, updatable=false)
	private Equipo equipo1;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo_fuera", insertable=false, updatable=false)
	private Equipo equipo2;

	public CalendarioLiga() {
	}

	public CalendarioLigaPK getId() {
		return this.id;
	}

	public void setId(CalendarioLigaPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Equipo getEquipo1() {
		return this.equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return this.equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

}