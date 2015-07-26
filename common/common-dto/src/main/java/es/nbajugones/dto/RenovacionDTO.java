package es.nbajugones.dto;

import es.nbajugones.dto.entities.Renovacione;

public class RenovacionDTO {

	private int year;
	
	private int tanda;
	
	private String jugador;
	
	private int idJugador;
	
	private String idEquipoProp;
	
	private String idEquipoGanador;
	
	private String logoEquipoProp;
	
	private String logoEquipoGanador;
	
	private String posicion;
	
	private double puntos;
	private double promedio;
	private double salario;
	private int years;
	private String renueva;
	
	public RenovacionDTO(Renovacione entity){
		this.idJugador = entity.getId().getIdJugador();
		this.tanda = entity.getTanda();
		this.idEquipoGanador = entity.getIdEquipoGanador();
		this.idEquipoProp = entity.getIdEquipoProp();
		this.jugador = entity.getJugador().getJugador();
		this.logoEquipoGanador =entity.getEquipoGanador()==null?"":entity.getEquipoGanador().getLogoDraft();
		this.logoEquipoProp = entity.getEquipoPropietario().getLogoDraft();
		this.posicion = entity.getPosicion();
		this.promedio = entity.getPromedio();
		this.puntos = entity.getPuntos();
		this.renueva = entity.getRenueva();
		this.salario = entity.getSalario()==null?0:entity.getSalario();
		this.year = entity.getId().getYear();
		this.years = entity.getYears()==null?0:entity.getYears();
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public int getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	public String getIdEquipoProp() {
		return idEquipoProp;
	}
	public void setIdEquipoProp(String idEquipoProp) {
		this.idEquipoProp = idEquipoProp;
	}
	public String getIdEquipoGanador() {
		return idEquipoGanador;
	}
	public void setIdEquipoGanador(String idEquipoGanador) {
		this.idEquipoGanador = idEquipoGanador;
	}
	public String getLogoEquipoProp() {
		return logoEquipoProp;
	}
	public void setLogoEquipoProp(String logoEquipoProp) {
		this.logoEquipoProp = logoEquipoProp;
	}
	public String getLogoEquipoGanador() {
		return logoEquipoGanador;
	}
	public void setLogoEquipoGanador(String logoEquipoGanador) {
		this.logoEquipoGanador = logoEquipoGanador;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public double getPuntos() {
		return puntos;
	}
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getRenueva() {
		return renueva;
	}
	public void setRenueva(String renueva) {
		this.renueva = renueva;
	}

	public int getTanda() {
		return tanda;
	}

	public void setTanda(int tanda) {
		this.tanda = tanda;
	}
	
	
	
	
}
