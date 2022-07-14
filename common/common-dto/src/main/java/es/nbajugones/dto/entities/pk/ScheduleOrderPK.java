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
public class ScheduleOrderPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "matchnumber")
    private int match;
    @Basic(optional = false)
    @Column(name = "team")
    private String team;
	@Basic(optional = false)
	@Column(name = "season")
	private String season;


    public ScheduleOrderPK() {
    }

    public ScheduleOrderPK(int match, String team, String season) {
        this.match = match;
        this.team = team;
		this.season = season;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
        hash += (int) match;
        hash += (team != null ? team.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleOrderPK)) {
            return false;
        }
        ScheduleOrderPK other = (ScheduleOrderPK) object;
        if (this.match != other.match) {
            return false;
        }
        if ((this.team == null && other.team != null) || (this.team != null && !this.team.equals(other.team))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.nbajugones.dto.entities.ScheduleOrderPK[ match=" + match + ", team=" + team + " ]";
    }
    
}
