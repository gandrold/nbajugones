package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.EquiposBonusPK;


/**
 * The persistent class for the equipos_bonus database table.
 * 
 */
@Entity
@Table(name="equipos_bonus")
public class EquiposBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EquiposBonusPK id;

	private String texto;

	public EquiposBonus() {
	}

	public EquiposBonusPK getId() {
		return this.id;
	}

	public void setId(EquiposBonusPK id) {
		this.id = id;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}