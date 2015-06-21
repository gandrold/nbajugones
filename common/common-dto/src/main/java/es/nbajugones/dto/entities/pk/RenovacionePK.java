package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the renovaciones database table.
 * 
 */
@Embeddable
public class RenovacionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="AÑO")
	private int year;

	@Column(name="ID_JUGADOR")
	private int idJugador;

	public RenovacionePK() {
	}
	
	public int getIdJugador() {
		return this.idJugador;
	}
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RenovacionePK)) {
			return false;
		}
		RenovacionePK castOther = (RenovacionePK)other;
		return 
			(this.year == castOther.year)
			&& (this.idJugador == castOther.idJugador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.year;
		hash = hash * prime + this.idJugador;
		
		return hash;
	}
}