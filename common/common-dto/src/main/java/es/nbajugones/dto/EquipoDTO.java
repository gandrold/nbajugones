package es.nbajugones.dto;

import java.util.List;

import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;

public class EquipoDTO {

	private Equipo equipo;
	
	private List<Jugadores> plantilla;

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Jugadores> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(List<Jugadores> plantilla) {
		this.plantilla = plantilla;
	}
	
	
	
}
