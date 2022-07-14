/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "schedule")
@NamedQueries({
	@NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")})
public class Schedule implements Serializable {
	@Basic(optional = false)
    @Column(name = "processed")
	private int processed;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
	private Collection<Stats> statsCollection;
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "eventid")
	private String eventid;
	@Basic(optional = false)
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
	private Date startdate;
	@Basic(optional = false)
    @Column(name = "hometeam")
	private String hometeam;
	@Basic(optional = false)
    @Column(name = "awayteam")
	private String awayteam;
	@Column(name = "homescore")
	private Integer homescore;
	@Column(name = "awayscore")
	private Integer awayscore;
	@Column(name = "season")
	private String season;
	@Column(name = "type")
	private String type;
	@Transient
	private int homeMatchNumber;
	@Transient
	private int awayMatchNumber;


	public Schedule() {
	}

	public Schedule(Integer id) {
		this.id = id;
	}

	public Schedule(Integer id, String eventid, Date startdate, String hometeam, String awayteam) {
		this.id = id;
		this.eventid = eventid;
		this.startdate = startdate;
		this.hometeam = hometeam;
		this.awayteam = awayteam;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getHometeam() {
		return hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}

	public String getAwayteam() {
		return awayteam;
	}

	public void setAwayteam(String awayteam) {
		this.awayteam = awayteam;
	}

	public Integer getHomescore() {
		return homescore;
	}

	public void setHomescore(Integer homescore) {
		this.homescore = homescore;
	}

	public Integer getAwayscore() {
		return awayscore;
	}

	public void setAwayscore(Integer awayscore) {
		this.awayscore = awayscore;
	}


	public int getHomeMatchNumber() {
		return homeMatchNumber;
	}

	public void setHomeMatchNumber(int homeMatchNumber) {
		this.homeMatchNumber = homeMatchNumber;
	}

	public int getAwayMatchNumber() {
		return awayMatchNumber;
	}

	public void setAwayMatchNumber(int awayMatchNumber) {
		this.awayMatchNumber = awayMatchNumber;
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
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "es.nbajugones.dto.entities.Schedule[ id=" + id + ", home="+hometeam+", away="+awayteam+" ]";
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



    @JsonIgnore
	public Collection<Stats> getStatsCollection() {
		return statsCollection;
	}

	public void setStatsCollection(Collection<Stats> statsCollection) {
		this.statsCollection = statsCollection;
	}

}
