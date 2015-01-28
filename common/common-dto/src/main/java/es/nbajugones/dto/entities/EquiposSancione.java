package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.EquiposSancionePK;


/**
 * The persistent class for the equipos_sanciones database table.
 * 
 */
@Entity
@Table(name="equipos_sanciones")
public class EquiposSancione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EquiposSancionePK id;

	private String texto;

	public EquiposSancione() {
	}

	public EquiposSancionePK getId() {
		return this.id;
	}

	public void setId(EquiposSancionePK id) {
		this.id = id;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}