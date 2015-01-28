package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the divisiones database table.
 * 
 */
@Embeddable
public class DivisionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int division;

	private String temporada;

	public DivisionePK() {
	}
	public int getDivision() {
		return this.division;
	}
	public void setDivision(int division) {
		this.division = division;
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
		if (!(other instanceof DivisionePK)) {
			return false;
		}
		DivisionePK castOther = (DivisionePK)other;
		return 
			(this.division == castOther.division)
			&& this.temporada.equals(castOther.temporada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.division;
		hash = hash * prime + this.temporada.hashCode();
		
		return hash;
	}
}