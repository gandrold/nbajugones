package es.nbajugones.dto;

import es.nbajugones.dto.entities.RondasDraft;


public class RondaDTO implements Comparable<RondaDTO>{
	private int ano;

	private int ronda;
	
	private String equipo;

	public RondaDTO(RondasDraft ronda){
		this.ano=ronda.getId().getAno();
		this.ronda=ronda.getId().getRonda();
		this.equipo=ronda.getEquipoRonda().getNombre();
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
	
	
}
