package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_EQUIPO")
	private String idEquipo;

	@Column(name="BONUS_ACT", nullable=false)
	private Double bonusAct;

	@Column(name="BONUS_ANT", nullable=false)
	private Double bonusAnt;

	@Column(name="CORTES", nullable=false)
	private Double cortes;

	@Column(name="EMAIL")
	private String email;

	@Column(name="LESIONADOS", nullable=false)
	private Double lesionados;

	@Column(name="LOGO")
	private String logo;

	@Column(name="LOGO_DRAFT")
	private String logoDraft;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PROPIETARIO")
	private String propietario;

	@Column(name="SANCIONES", nullable=false)
	private Double sanciones;

	//bi-directional many-to-one association to CalendarioLiga
	@OneToMany(mappedBy="equipo1")
	private List<CalendarioLiga> calendarioLigas1;

	//bi-directional many-to-one association to CalendarioLiga
	@OneToMany(mappedBy="equipo2")
	private List<CalendarioLiga> calendarioLigas2;

	public Equipo() {
	}

	public String getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Double getBonusAct() {
		return this.bonusAct;
	}

	public void setBonusAct(Double bonusAct) {
		this.bonusAct = bonusAct;
	}

	public Double getBonusAnt() {
		return this.bonusAnt;
	}

	public void setBonusAnt(Double bonusAnt) {
		this.bonusAnt = bonusAnt;
	}

	public Double getCortes() {
		return this.cortes;
	}

	public void setCortes(Double cortes) {
		this.cortes = cortes;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getLesionados() {
		return this.lesionados;
	}

	public void setLesionados(Double lesionados) {
		this.lesionados = lesionados;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogoDraft() {
		return this.logoDraft;
	}

	public void setLogoDraft(String logoDraft) {
		this.logoDraft = logoDraft;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPropietario() {
		return this.propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public Double getSanciones() {
		return this.sanciones;
	}

	public void setSanciones(Double sanciones) {
		this.sanciones = sanciones;
	}

	public List<CalendarioLiga> getCalendarioLigas1() {
		return this.calendarioLigas1;
	}

	public void setCalendarioLigas1(List<CalendarioLiga> calendarioLigas1) {
		this.calendarioLigas1 = calendarioLigas1;
	}

	public CalendarioLiga addCalendarioLigas1(CalendarioLiga calendarioLigas1) {
		getCalendarioLigas1().add(calendarioLigas1);
		calendarioLigas1.setEquipo1(this);

		return calendarioLigas1;
	}

	public CalendarioLiga removeCalendarioLigas1(CalendarioLiga calendarioLigas1) {
		getCalendarioLigas1().remove(calendarioLigas1);
		calendarioLigas1.setEquipo1(null);

		return calendarioLigas1;
	}

	public List<CalendarioLiga> getCalendarioLigas2() {
		return this.calendarioLigas2;
	}

	public void setCalendarioLigas2(List<CalendarioLiga> calendarioLigas2) {
		this.calendarioLigas2 = calendarioLigas2;
	}

	public CalendarioLiga addCalendarioLigas2(CalendarioLiga calendarioLigas2) {
		getCalendarioLigas2().add(calendarioLigas2);
		calendarioLigas2.setEquipo2(this);

		return calendarioLigas2;
	}

	public CalendarioLiga removeCalendarioLigas2(CalendarioLiga calendarioLigas2) {
		getCalendarioLigas2().remove(calendarioLigas2);
		calendarioLigas2.setEquipo2(null);

		return calendarioLigas2;
	}

}