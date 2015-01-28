package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the draft_inicial database table.
 * 
 */
@Entity
@Table(name="draft_inicial")
public class DraftInicial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int posicion;

	@Column(name="ID_EQUIPO_PROP")
	private String idEquipoProp;

	@Column(name="ID_JUGADOR")
	private String idJugador;

	public DraftInicial() {
	}

	public int getPosicion() {
		return this.posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getIdEquipoProp() {
		return this.idEquipoProp;
	}

	public void setIdEquipoProp(String idEquipoProp) {
		this.idEquipoProp = idEquipoProp;
	}

	public String getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

}