/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import es.nbajugones.dto.entities.pk.DeadlinesPK;

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
@Table(name = "deadlines")
@NamedQueries({
    @NamedQuery(name = "Deadlines.findAll", query = "SELECT d FROM Deadlines d")})
public class Deadlines implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeadlinesPK deadlinesPK;
    @Column(name = "date")
    private Integer date;

    public Deadlines() {
    }

    public Deadlines(DeadlinesPK deadlinesPK) {
        this.deadlinesPK = deadlinesPK;
    }

    public Deadlines(String season, int matchid) {
        this.deadlinesPK = new DeadlinesPK(season, matchid);
    }

    public DeadlinesPK getDeadlinesPK() {
        return deadlinesPK;
    }

    public void setDeadlinesPK(DeadlinesPK deadlinesPK) {
        this.deadlinesPK = deadlinesPK;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deadlinesPK != null ? deadlinesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deadlines)) {
            return false;
        }
        Deadlines other = (Deadlines) object;
        if ((this.deadlinesPK == null && other.deadlinesPK != null) || (this.deadlinesPK != null && !this.deadlinesPK.equals(other.deadlinesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.Deadlines[ deadlinesPK=" + deadlinesPK + " ]";
    }
    
}
