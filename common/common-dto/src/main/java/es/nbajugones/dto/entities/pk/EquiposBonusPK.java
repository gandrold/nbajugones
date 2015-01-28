package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the equipos_bonus database table.
 * 
 */
@Embeddable
public class EquiposBonusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	private String temporada;

	@Column(name="ID_BONUS")
	private int idBonus;

	public EquiposBonusPK() {
	}
	public String getIdEquipo() {
		return this.idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getTemporada() {
		return this.temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public int getIdBonus() {
		return this.idBonus;
	}
	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EquiposBonusPK)) {
			return false;
		}
		EquiposBonusPK castOther = (EquiposBonusPK)other;
		return 
			this.idEquipo.equals(castOther.idEquipo)
			&& this.temporada.equals(castOther.temporada)
			&& (this.idBonus == castOther.idBonus);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipo.hashCode();
		hash = hash * prime + this.temporada.hashCode();
		hash = hash * prime + this.idBonus;
		
		return hash;
	}
}