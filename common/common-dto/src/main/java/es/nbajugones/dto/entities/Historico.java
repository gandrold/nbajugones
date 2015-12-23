package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.HistoricoPK;


/**
 * The persistent class for the historico database table.
 *
 */
@Entity
@Table(name="historico")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoricoPK id;

	@Column(name="conferencia")
	private int conferencia;

	@Column(name="division")
	private int division;

	@Column(name="ganados")
	private int ganados;

	@Column(name="logros")
	private String logros;

	@Column(name="media")
	private double media;

	@Column(name="perdidos")
	private int perdidos;

	public Historico() {
	}

	public HistoricoPK getId() {
		return this.id;
	}

	public void setId(HistoricoPK id) {
		this.id = id;
	}

	public int getConferencia() {
		return this.conferencia;
	}

	public void setConferencia(int conferencia) {
		this.conferencia = conferencia;
	}

	public int getDivision() {
		return this.division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public int getGanados() {
		return this.ganados;
	}

	public void setGanados(int ganados) {
		this.ganados = ganados;
	}

	public String getLogros() {
		return this.logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	public double getMedia() {
		return this.media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public int getPerdidos() {
		return this.perdidos;
	}

	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}

}