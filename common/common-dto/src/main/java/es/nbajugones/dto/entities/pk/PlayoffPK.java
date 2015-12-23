package es.nbajugones.dto.entities.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the historico database table.
 *
 */
@Embeddable
public class PlayoffPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;



	@Column(name="temporada")
	private String temporada;

	@Column(name="ronda")
	private int ronda;

	@Column(name="partido")
	private int partido;


	public PlayoffPK() {
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public int getPartido() {
		return partido;
	}

	public void setPartido(int partido) {
		this.partido = partido;
	}



}