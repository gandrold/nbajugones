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

	@Column(name="BONUS_ACT")
	private Double bonusAct;

	@Column(name="BONUS_ANT")
	private Double bonusAnt;

	@Column(name="CORTES")
	private Double cortes;

	@Column(name="EMAIL")
	private String email;

	@Column(name="LESIONADOS")
	private Double lesionados;

	@Column(name="LOGO")
	private String logo;

	@Column(name="LOGO_DRAFT")
	private String logoDraft;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PROPIETARIO")
	private String propietario;

	@Column(name="SANCIONES")
	private Double sanciones;

	//bi-directional many-to-one association to CalendarioLiga
	@OneToMany(mappedBy="equipo1")
	private List<CalendarioLiga> calendarioLigas1;

	//bi-directional many-to-one association to CalendarioLiga
	@OneToMany(mappedBy="equipo2")
	private List<CalendarioLiga> calendarioLigas2;

		@OneToMany(mappedBy="id.idEquipo")
		private List<Plantilla> plantilla;

		@OneToMany(mappedBy="idEquipo")
		private List<Derecho> derechos;

		@OneToMany(mappedBy="idEquipoProp")
		private List<RondasDraft> rondas;

		@OneToMany(mappedBy="id.idEquipo")
		private List<Historico> historico;

		@OneToMany(mappedBy="idEquipo")
		private List<Log> log;


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

	public List<Plantilla> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(List<Plantilla> plantilla) {
		this.plantilla = plantilla;
	}

	public List<Derecho> getDerechos() {
		return derechos;
	}

	public void setDerechos(List<Derecho> derechos) {
		this.derechos = derechos;
	}

	public List<RondasDraft> getRondas() {
		return rondas;
	}

	public void setRondas(List<RondasDraft> rondas) {
		this.rondas = rondas;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public List<Log> getLog() {
		return log;
	}

	public void setLog(List<Log> log) {
		this.log = log;
	}


	public boolean checkPlayer(int id){
		for (Plantilla p:plantilla){
			if (p.getId().getIdJugador() == id){
				return true;
			}
		}
		return false;
	}



}