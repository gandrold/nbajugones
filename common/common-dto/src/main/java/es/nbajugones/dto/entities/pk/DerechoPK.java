package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the derechos database table.
 * 
 */
@Embeddable
public class DerechoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	private String jugador;

	public DerechoPK() {
	}
	public String getIdEquipo() {
		return this.idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getJugador() {
		return this.jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DerechoPK)) {
			return false;
		}
		DerechoPK castOther = (DerechoPK)other;
		return 
			this.idEquipo.equals(castOther.idEquipo)
			&& this.jugador.equals(castOther.jugador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipo.hashCode();
		hash = hash * prime + this.jugador.hashCode();
		
		return hash;
	}
}