package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.ConferenciaPK;


/**
 * The persistent class for the conferencias database table.
 * 
 */
@Entity
@Table(name="conferencias")
public class Conferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConferenciaPK id;

	private String nombre;

	public Conferencia() {
	}

	public ConferenciaPK getId() {
		return this.id;
	}

	public void setId(ConferenciaPK id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}