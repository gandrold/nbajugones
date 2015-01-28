package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rondas_draft database table.
 * 
 */
@Embeddable
public class RondasDraftPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ano")
	private int ano;

	@Column(name="ronda")
	private int ronda;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	public RondasDraftPK() {
	}
	public int getAno() {
		return this.ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getRonda() {
		return this.ronda;
	}
	public void setRonda(int ronda) {
		this.ronda = ronda;
	}
	public String getIdEquipo() {
		return this.idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RondasDraftPK)) {
			return false;
		}
		RondasDraftPK castOther = (RondasDraftPK)other;
		return 
			(this.ano == castOther.ano)
			&& (this.ronda == castOther.ronda)
			&& this.idEquipo.equals(castOther.idEquipo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ano;
		hash = hash * prime + this.ronda;
		hash = hash * prime + this.idEquipo.hashCode();
		
		return hash;
	}
}