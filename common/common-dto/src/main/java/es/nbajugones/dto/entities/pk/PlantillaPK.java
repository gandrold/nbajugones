package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plantillas database table.
 * 
 */
@Embeddable
public class PlantillaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	@Column(name="ID_JUGADOR")
	private int idJugador;

	public PlantillaPK() {
	}
	public String getIdEquipo() {
		return this.idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public int getIdJugador() {
		return this.idJugador;
	}
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlantillaPK)) {
			return false;
		}
		PlantillaPK castOther = (PlantillaPK)other;
		return 
			this.idEquipo.equals(castOther.idEquipo)
			&& (this.idJugador == castOther.idJugador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipo.hashCode();
		hash = hash * prime + this.idJugador;
		
		return hash;
	}
}