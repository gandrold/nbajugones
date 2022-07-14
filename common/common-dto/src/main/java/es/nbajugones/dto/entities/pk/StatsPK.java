/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author iblanco
 */
@Embeddable
public class StatsPK implements Serializable {
	@Basic(optional = false)
    @Column(name = "id_jugador")
	private int idJugador;
	@Basic(optional = false)
    @Column(name = "id_equipo")
	private String idEquipo;
	@Basic(optional = false)
    @Column(name = "id_partido")
	private int idPartido;

	public StatsPK() {
	}

	public StatsPK(int idJugador, String idEquipo, int idPartido) {
		this.idJugador = idJugador;
		this.idEquipo = idEquipo;
		this.idPartido = idPartido;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idJugador;
		hash += (idEquipo != null ? idEquipo.hashCode() : 0);
		hash += (int) idPartido;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof StatsPK)) {
			return false;
		}
		StatsPK other = (StatsPK) object;
		if (this.idJugador != other.idJugador) {
			return false;
		}
		if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
			return false;
		}
		if (this.idPartido != other.idPartido) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "es.nbajugones.dto.entities.StatsPK[ idJugador=" + idJugador + ", idEquipo=" + idEquipo + ", jornada=" + idPartido + " ]";
	}

}
