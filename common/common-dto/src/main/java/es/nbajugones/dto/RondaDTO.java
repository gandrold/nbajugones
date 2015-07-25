package es.nbajugones.dto;

import es.nbajugones.dto.entities.RondasDraft;


public class RondaDTO implements Comparable<RondaDTO>{
	private int ano;

	private int ronda;
	
	private String equipo;
	
	private String equipoLogo;
	
	private String equipoProp;
	
	private String equipoPropLogo;
	
	private String idEquipo;
	
	private String jugador;
	
	private String posicion;
	
	private int orden;

	public RondaDTO(RondasDraft ronda){
		this.ano=ronda.getId().getAno();
		this.ronda=ronda.getId().getRonda();
		this.equipo=ronda.getEquipoRonda().getNombre();
		this.setEquipoLogo(ronda.getEquipoRonda().getLogoDraft());
		this.equipoProp = ronda.getEquipoPropietario().getNombre();
		this.setEquipoPropLogo(ronda.getEquipoPropietario().getLogoDraft());
		this.idEquipo = ronda.getId().getIdEquipo();
		this.setJugador(ronda.getJugador());
		this.setPosicion(ronda.getLetra());
	}
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public int compareTo(RondaDTO o) {
		if (ano==o.ano){
			if (ronda==o.ronda){
				return equipo.compareTo(o.equipo);
			} else {
				return ronda-o.ronda;
			}
		} else {
			return ano-o.ano;
		}
		
	}
	public String getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getEquipoProp() {
		return equipoProp;
	}
	public void setEquipoProp(String equipoProp) {
		this.equipoProp = equipoProp;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getEquipoPropLogo() {
		return equipoPropLogo;
	}
	public void setEquipoPropLogo(String equipoPropLogo) {
		this.equipoPropLogo = equipoPropLogo;
	}
	public String getEquipoLogo() {
		return equipoLogo;
	}
	public void setEquipoLogo(String equipoLogo) {
		this.equipoLogo = equipoLogo;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	
}
