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
public class RosterPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_jugador")
    private int idJugador;
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private String idEquipo;
    @Basic(optional = false)
    @Column(name = "game")
    private int game;
	@Basic(optional = false)
	@Column(name = "season")
	private String season;

    public RosterPK() {
    }

    public RosterPK(int idJugador, String idEquipo, int game) {
        this.idJugador = idJugador;
        this.idEquipo = idEquipo;
        this.game = game;
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

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idJugador;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        hash += (int) game;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RosterPK)) {
            return false;
        }
        RosterPK other = (RosterPK) object;
        if (this.idJugador != other.idJugador) {
            return false;
        }
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        if (this.game != other.game) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.RosterPK[ idJugador=" + idJugador + ", idEquipo=" + idEquipo + ", game=" + game + " ]";
    }
    
}
