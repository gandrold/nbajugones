package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.PlayoffPK;


/**
 * The persistent class for the historico database table.
 *
 */
@Entity
@Table(name="playoff")
public class Playoff implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlayoffPK id;

	@Column(name="resultado1")
	private int resultado1;

	@Column(name="resultado2")
	private int resultado2;

	@Column(name="equipo1")
	private String equipo1;

	@Column(name="equipo2")
	private String equipo2;


	public Playoff() {
	}

	public PlayoffPK getId() {
		return id;
	}

	public void setId(PlayoffPK id) {
		this.id = id;
	}

	public int getResultado1() {
		return resultado1;
	}

	public void setResultado1(int resultado1) {
		this.resultado1 = resultado1;
	}

	public int getResultado2() {
		return resultado2;
	}

	public void setResultado2(int resultado2) {
		this.resultado2 = resultado2;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}




}