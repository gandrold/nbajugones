/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import es.nbajugones.dto.entities.pk.RosterPK;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "roster")
@NamedQueries({
    @NamedQuery(name = "Roster.findAll", query = "SELECT r FROM Roster r")})
public class Roster implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RosterPK rosterPK;
    @Column(name = "spot")
    private Integer spot;
    @Column(name = "posicion")
    private String posicion;

    public Roster() {
    }

    public Roster(RosterPK rosterPK) {
        this.rosterPK = rosterPK;
    }

    public Roster(int idJugador, String idEquipo, int game) {
        this.rosterPK = new RosterPK(idJugador, idEquipo, game);
    }

    public RosterPK getRosterPK() {
        return rosterPK;
    }

    public void setRosterPK(RosterPK rosterPK) {
        this.rosterPK = rosterPK;
    }

    public Integer getSpot() {
        return spot;
    }

    public void setSpot(Integer spot) {
        this.spot = spot;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rosterPK != null ? rosterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roster)) {
            return false;
        }
        Roster other = (Roster) object;
        if ((this.rosterPK == null && other.rosterPK != null) || (this.rosterPK != null && !this.rosterPK.equals(other.rosterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.Roster[ rosterPK=" + rosterPK + " ]";
    }
    
}
