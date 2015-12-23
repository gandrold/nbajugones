package es.nbajugones.dto;

import es.nbajugones.dto.entities.Copa;
import es.nbajugones.dto.entities.Playoff;
import java.io.Serializable;




public class PlayoffDTO implements Serializable {


	private int idPartido;

	private int ronda;

	private EquipoDTO equipo1;

	private EquipoDTO equipo2;

	private int resultado1;

	private int resultado2;

	public PlayoffDTO(Playoff copa, EquipoDTO equipoCasa, EquipoDTO equipoFuera) {
		idPartido = copa.getId().getPartido();
		ronda = copa.getId().getRonda();
		this.equipo1 = equipoCasa;
		this.equipo2 = equipoFuera;
		this.resultado1 = copa.getResultado1();
		this.resultado2 = copa.getResultado2();
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public EquipoDTO getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(EquipoDTO equipo1) {
		this.equipo1 = equipo1;
	}

	public EquipoDTO getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(EquipoDTO equipo2) {
		this.equipo2 = equipo2;
	}

	public int getResultado1() {
		return resultado1;
	}

	public void setResultado1(int resultado1) {
		this.resultado1 = resultado1;
	}

	public int getResultado2() {
		return resultado2;
	}

	public void setResultado2(int resultado2) {
		this.resultado2 = resultado2;
	}



	public boolean isGanador1(){
		return resultado1 > resultado2;
	}

	public boolean isGanador2(){
		return resultado1 < resultado2;
	}

}