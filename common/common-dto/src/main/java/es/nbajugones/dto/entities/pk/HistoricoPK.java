package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the historico database table.
 * 
 */
@Embeddable
public class HistoricoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	private String temporada;

	public HistoricoPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HistoricoPK)) {
			return false;
		}
		HistoricoPK castOther = (HistoricoPK)other;
		return 
			this.idEquipo.equals(castOther.idEquipo)
			&& this.temporada.equals(castOther.temporada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipo.hashCode();
		hash = hash * prime + this.temporada.hashCode();
		
		return hash;
	}
}