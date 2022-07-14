package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the copa database table.
 * 
 */
@Embeddable
public class CopaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String temporada;

	private int ronda;

	private int partido;

	public CopaPK() {
	}

	public CopaPK(String temporada, int ronda, int partido) {
		this.temporada = temporada;
		this.ronda = ronda;
		this.partido = partido;
	}

	public String getTemporada() {
		return this.temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public int getRonda() {
		return this.ronda;
	}
	public void setRonda(int ronda) {
		this.ronda = ronda;
	}
	public int getPartido() {
		return this.partido;
	}
	public void setPartido(int partido) {
		this.partido = partido;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CopaPK)) {
			return false;
		}
		CopaPK castOther = (CopaPK)other;
		return 
			this.temporada.equals(castOther.temporada)
			&& (this.ronda == castOther.ronda)
			&& (this.partido == castOther.partido);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.temporada.hashCode();
		hash = hash * prime + this.ronda;
		hash = hash * prime + this.partido;
		
		return hash;
	}
}