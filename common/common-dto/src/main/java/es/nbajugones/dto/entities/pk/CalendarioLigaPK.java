package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the calendario_liga database table.
 * 
 */
@Embeddable
public class CalendarioLigaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String temporada;

	@Column(name="id_equipo_casa")
	private String idEquipoCasa;

	private int jornada;

	public CalendarioLigaPK() {
	}
	public String getTemporada() {
		return this.temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getIdEquipoCasa() {
		return this.idEquipoCasa;
	}
	public void setIdEquipoCasa(String idEquipoCasa) {
		this.idEquipoCasa = idEquipoCasa;
	}
	public int getJornada() {
		return this.jornada;
	}
	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CalendarioLigaPK)) {
			return false;
		}
		CalendarioLigaPK castOther = (CalendarioLigaPK)other;
		return 
			this.temporada.equals(castOther.temporada)
			&& this.idEquipoCasa.equals(castOther.idEquipoCasa)
			&& (this.jornada == castOther.jornada);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.temporada.hashCode();
		hash = hash * prime + this.idEquipoCasa.hashCode();
		hash = hash * prime + this.jornada;
		
		return hash;
	}
}