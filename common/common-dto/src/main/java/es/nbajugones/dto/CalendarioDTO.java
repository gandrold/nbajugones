package es.nbajugones.dto;

import java.util.Date;

import es.nbajugones.dto.entities.CalendarioLiga;

public class CalendarioDTO {
	
	
	private Date fecha;

	private String url;

	private String contrario;
	
	private String logoContrario;
	
	private boolean fuera;
	
	public CalendarioDTO(CalendarioLiga calendario, boolean fuera){
		this.fecha = calendario.getFecha();
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

	
	
}
