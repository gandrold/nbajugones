package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the equipos_sanciones database table.
 * 
 */
@Embeddable
public class EquiposSancionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	@Column(name="ID_SANCION")
	private int idSancion;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	public EquiposSancionePK() {
	}
	public String getIdEquipo() {
		return this.idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public int getIdSancion() {
		return this.idSancion;
	}
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EquiposSancionePK)) {
			return false;
		}
		EquiposSancionePK castOther = (EquiposSancionePK)other;
		return 
			this.idEquipo.equals(castOther.idEquipo)
			&& (this.idSancion == castOther.idSancion)
			&& this.fecha.equals(castOther.fecha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEquipo.hashCode();
		hash = hash * prime + this.idSancion;
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
	}
}