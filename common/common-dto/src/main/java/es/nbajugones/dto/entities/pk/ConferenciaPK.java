package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the conferencias database table.
 * 
 */
@Embeddable
public class ConferenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int conferencia;

	private String temporada;

	public ConferenciaPK() {
	}
	public int getConferencia() {
		return this.conferencia;
	}
	public void setConferencia(int conferencia) {
		this.conferencia = conferencia;
	}
	public String getTemporada() {
		return this.temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConferenciaPK)) {
			return false;
		}
		ConferenciaPK castOther = (ConferenciaPK)other;
		return 
			(this.conferencia == castOther.conferencia)
			&& this.temporada.equals(castOther.temporada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.conferencia;
		hash = hash * prime + this.temporada.hashCode();
		
		return hash;
	}
}