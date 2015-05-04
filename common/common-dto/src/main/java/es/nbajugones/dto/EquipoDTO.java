package es.nbajugones.dto;

import java.util.ArrayList;
import java.util.List;

import es.nbajugones.dto.entities.CalendarioLiga;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Historico;
import es.nbajugones.dto.entities.Jugadores;

public class EquipoDTO {
	
	private String idEquipo;

	private Double bonusAct;

	private Double bonusAnt;

	private Double cortes;

	private String email;

	private Double lesionados;

	private String logo;

	private String logoDraft;

	private String nombre;

	private String propietario;

	private Double sanciones;
	
	private List<JugadorDTO> plantilla;
	
	private List<HistoricoDTO> historico;

	public EquipoDTO(Equipo equipo, List<Jugadores> plantilla){
		this.bonusAct = equipo.getBonusAct();
		this.bonusAnt = equipo.getBonusAnt();
		this.cortes = equipo.getCortes();
		this.email = equipo.getEmail();
		this.idEquipo = equipo.getIdEquipo();
		this.lesionados = equipo.getLesionados();
		this.logo = equipo.getLogo();
		this.logoDraft = equipo.getLogoDraft();
		this.nombre = equipo.getNombre();
		this.propietario = equipo.getPropietario();
		this.sanciones = equipo.getSanciones();
		this.plantilla = new ArrayList<JugadorDTO>();
		for (Jugadores p:plantilla){
			this.plantilla.add(new JugadorDTO(p));
		}
		historico = new ArrayList<HistoricoDTO>();
		for (Historico h: equipo.getHistorico()){
			historico.add(new HistoricoDTO(h));
		}
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Double getBonusAct() {
		return bonusAct;
	}

	public void setBonusAct(Double bonusAct) {
		this.bonusAct = bonusAct;
	}

	public Double getBonusAnt() {
		return bonusAnt;
	}

	public void setBonusAnt(Double bonusAnt) {
		this.bonusAnt = bonusAnt;
	}

	public Double getCortes() {
		return cortes;
	}

	public void setCortes(Double cortes) {
		this.cortes = cortes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getLesionados() {
		return lesionados;
	}

	public void setLesionados(Double lesionados) {
		this.lesionados = lesionados;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogoDraft() {
		return logoDraft;
	}

	public void setLogoDraft(String logoDraft) {
		this.logoDraft = logoDraft;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public Double getSanciones() {
		return sanciones;
	}

	public void setSanciones(Double sanciones) {
		this.sanciones = sanciones;
	}

	public List<JugadorDTO> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(List<JugadorDTO> plantilla) {
		this.plantilla = plantilla;
	}

	public List<HistoricoDTO> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoDTO> historico) {
		this.historico = historico;
	}

	
	
}
