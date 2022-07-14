/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto.entities;

import java.io.Serializable;
import java.util.Collection;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author iblanco
 */
@Entity
@Table(name = "players")
@NamedQueries({
	@NamedQuery(name = "Players.findAll", query = "SELECT p FROM Players p")})
public class Players implements Serializable {
	@Basic(optional = false)
    @Column(name = "canPlayG")
	private int canPlayG;
	@Basic(optional = false)
    @Column(name = "canPlayF")
	private int canPlayF;
	@Basic(optional = false)
    @Column(name = "canPlayC")
	private int canPlayC;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "players")
	private Collection<Stats> statsCollection;

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "firstname")
	private String firstname;
	@Basic(optional = false)
	@Column(name = "lastname")
	private String lastname;
	@Basic(optional = false)
	@Column(name = "displayname")
	private String displayname;

	public Players() {
		canPlayC = 0;
		canPlayF = 0;
		canPlayG = 0;
	}

	public Players(Integer id) {
		this.id = id;
	}

	public Players(Integer id, String firstname, String lastname, String displayname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.displayname = displayname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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
		if (!(object instanceof Players)) {
			return false;
		}
		Players other = (Players) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "es.nbajugones.dto.entities.Players[ id=" + id + ", name=" + displayname + " ]";
	}

	public int getCanPlayG() {
		return canPlayG;
	}

	public void setCanPlayG(int canPlayG) {
		this.canPlayG = canPlayG;
	}

	public int getCanPlayF() {
		return canPlayF;
	}

	public void setCanPlayF(int canPlayF) {
		this.canPlayF = canPlayF;
	}

	public int getCanPlayC() {
		return canPlayC;
	}

	public void setCanPlayC(int canPlayC) {
		this.canPlayC = canPlayC;
	}

    @JsonIgnore
	public Collection<Stats> getStatsCollection() {
		return statsCollection;
	}

	public void setStatsCollection(Collection<Stats> statsCollection) {
		this.statsCollection = statsCollection;
	}

}
