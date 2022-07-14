/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "season")
@NamedQueries({
	@NamedQuery(name = "Season.findAll", query = "SELECT s FROM Season s")})
public class Season implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @Column(name = "season")
	private String season;
	@Column(name = "start")
    @Temporal(TemporalType.DATE)
	private Date start;
	@Column(name = "end")
    @Temporal(TemporalType.DATE)
	private Date end;
	@Column(name = "lastprocessed")
    @Temporal(TemporalType.DATE)
	private Date lastprocessed;

	public Season() {
	}

	public Season(String season) {
		this.season = season;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getLastprocessed() {
		return lastprocessed;
	}

	public void setLastprocessed(Date lastprocessed) {
		this.lastprocessed = lastprocessed;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (season != null ? season.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Season)) {
			return false;
		}
		Season other = (Season) object;
		if ((this.season == null && other.season != null) || (this.season != null && !this.season.equals(other.season))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "es.nbajugones.dto.entities.Season[ season=" + season + " ]";
	}

}
