package es.nbajugones.dto;

import es.nbajugones.dto.entities.Historico;

public class HistoricoDTO implements Comparable<HistoricoDTO>{

	private String temporada;

	private int ganados;

	private int perdidos;

	private String logros;

	private double media;

	private String nombreEquipo;

	public HistoricoDTO(Historico historico){
		this.ganados = historico.getGanados();
		this.logros = historico.getLogros();
		this.perdidos = historico.getPerdidos();
		this.media = historico.getMedia();
		this.temporada =  historico.getId().getTemporada();
	}

	public HistoricoDTO(Historico historico, String nombreEquipo){
		this.ganados = historico.getGanados();
		this.logros = historico.getLogros();
		this.perdidos = historico.getPerdidos();
		this.media = historico.getMedia();
		this.temporada =  historico.getId().getTemporada();
		this.nombreEquipo = nombreEquipo;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public int getGanados() {
		return ganados;
	}

	public void setGanados(int ganados) {
		this.ganados = ganados;
	}

	public int getPerdidos() {
		return perdidos;
	}

	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}

	public String getLogros() {
		return logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public int compareTo(HistoricoDTO o) {

		return o.temporada.compareTo(temporada);
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}



}
