package es.nbajugones.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import es.nbajugones.dto.entities.CalendarioLiga;
import es.nbajugones.utils.DateFormatter;

public class CalendarioDTO implements Comparable<CalendarioDTO>{
	
	private int jornada;
	
	private String temporada;
	
	private String fecha;

	private String url;

	private String contrario;
	
	private String logoContrario;
	
	private boolean fuera;
	
	public CalendarioDTO(CalendarioLiga calendario, boolean fuera){
		this.jornada = calendario.getId().getJornada();
		this.temporada = calendario.getId().getTemporada();
		this.fecha = DateFormatter.formatDate("dd/MM/yyyy", calendario.getFecha());
		this.url = calendario.getUrl();
		this.fuera = fuera;
		if (!fuera){
			contrario = calendario.getEquipo2().getIdEquipo();
			logoContrario = calendario.getEquipo2().getLogoDraft();
		} else {
			contrario = calendario.getEquipo1().getIdEquipo();
			logoContrario = calendario.getEquipo1().getLogoDraft();
		}
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFuera() {
		return fuera;
	}

	public void setFuera(boolean fuera) {
		this.fuera = fuera;
	}

	public String getContrario() {
		return contrario;
	}

	public void setContrario(String contrario) {
		this.contrario = contrario;
	}

	public String getLogoContrario() {
		return logoContrario;
	}

	public void setLogoContrario(String logoContrario) {
		this.logoContrario = logoContrario;
	}

	public int compareTo(CalendarioDTO o) {
		try{
			return DateFormatter.convertToDate("dd/MM/yyyy", fecha).compareTo(DateFormatter.convertToDate("dd/MM/yyyy", o.fecha));
		} catch (Exception e){
			return 0;
		}
		
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	
	
}
