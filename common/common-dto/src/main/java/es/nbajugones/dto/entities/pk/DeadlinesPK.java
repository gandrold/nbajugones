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
public class DeadlinesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "season")
    private String season;
    @Basic(optional = false)
    @Column(name = "matchid")
    private int matchid;

    public DeadlinesPK() {
    }

    public DeadlinesPK(String season, int matchid) {
        this.season = season;
        this.matchid = matchid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getMatchid() {
        return matchid;
    }

    public void setMatchid(int matchid) {
        this.matchid = matchid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (season != null ? season.hashCode() : 0);
        hash += (int) matchid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeadlinesPK)) {
            return false;
        }
        DeadlinesPK other = (DeadlinesPK) object;
        if ((this.season == null && other.season != null) || (this.season != null && !this.season.equals(other.season))) {
            return false;
        }
        if (this.matchid != other.matchid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.DeadlinesPK[ season=" + season + ", matchid=" + matchid + " ]";
    }
    
}
