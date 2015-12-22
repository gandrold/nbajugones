package es.nbajugones.dto;

import es.nbajugones.dto.entities.Copa;
import java.io.Serializable;




public class CopaDTO implements Serializable {


	private int idPartido;

	private int ronda;

	private EquipoDTO equipoCasa;

	private EquipoDTO equipoFuera;

	private Double puntosCasa;

	private Double puntosFuera;

	private String url;

	public CopaDTO(Copa copa, EquipoDTO equipoCasa, EquipoDTO equipoFuera) {
		idPartido = copa.getId().getPartido();
		ronda = copa.getId().getRonda();
		this.equipoCasa = equipoCasa;
		this.equipoFuera = equipoFuera;
		this.puntosCasa = copa.getPuntosCasa()!=null?copa.getPuntosCasa():0.0;
		this.puntosFuera = copa.getPuntosFuera()!=null?copa.getPuntosFuera():0.0;
		this.url = copa.getUrl();
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

	public EquipoDTO getEquipoCasa() {
		return equipoCasa;
	}

	public void setEquipoCasa(EquipoDTO equipoCasa) {
		this.equipoCasa = equipoCasa;
	}

	public EquipoDTO getEquipoFuera() {
		return equipoFuera;
	}

	public void setEquipoFuera(EquipoDTO equipoFuera) {
		this.equipoFuera = equipoFuera;
	}



	public Double getPuntosCasa() {
		return this.puntosCasa;
	}

	public void setPuntosCasa(Double puntosCasa) {
		this.puntosCasa = puntosCasa;
	}

	public Double getPuntosFuera() {
		return this.puntosFuera;
	}

	public void setPuntosFuera(Double puntosFuera) {
		this.puntosFuera = puntosFuera;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isCasaGanador(){
		if (puntosCasa!=null && puntosFuera !=null){
			return puntosCasa > puntosFuera;
		} else {
			return false;
		}
	}
	
	public boolean isFueraGanador(){
		if (puntosCasa!=null && puntosFuera !=null){
			return puntosCasa < puntosFuera;
		} else {
			return false;
		}
	}

}