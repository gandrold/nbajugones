package es.nbajugones.dto;

import es.nbajugones.dto.entities.RondasDraft;


public class RondaDTO implements Comparable<RondaDTO>{
	private int ano;

	private int ronda;
	
	private String equipo;
	
	private String equipoProp;
	
	private String idEquipo;

	public RondaDTO(RondasDraft ronda){
		this.ano=ronda.getId().getAno();
		this.ronda=ronda.getId().getRonda();
		this.equipo=ronda.getEquipoRonda().getNombre();
		this.equipoProp = ronda.getEquipoPropietario().getNombre();
		this.idEquipo = ronda.getId().getIdEquipo();
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
	
	
}
