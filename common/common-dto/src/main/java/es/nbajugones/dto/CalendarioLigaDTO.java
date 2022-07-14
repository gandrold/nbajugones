package es.nbajugones.dto;

import es.nbajugones.dto.entities.CalendarioLiga;

import java.util.Date;

/**
 * Created by iblanco on 08/11/2016.
 */
public class CalendarioLigaDTO {

	private Date date;

	private String homeLogo;

	private String awayLogo;

	private String link;

	public CalendarioLigaDTO(CalendarioLiga calendarioLiga) {
		this.date = calendarioLiga.getFecha();
		this.homeLogo = calendarioLiga.getEquipo1().getLogoDraft();
		this.awayLogo = calendarioLiga.getEquipo2().getLogoDraft();
		this.link = calendarioLiga.getUrl();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHomeLogo() {
		return homeLogo;
	}

	public void setHomeLogo(String homeLogo) {
		this.homeLogo = homeLogo;
	}

	public String getAwayLogo() {
		return awayLogo;
	}

	public void setAwayLogo(String awayLogo) {
		this.awayLogo = awayLogo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
