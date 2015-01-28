package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.DivisionePK;


/**
 * The persistent class for the divisiones database table.
 * 
 */
@Entity
@Table(name="divisiones")
public class Divisione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DivisionePK id;

	private String nombre;

	public Divisione() {
	}

	public DivisionePK getId() {
		return this.id;
	}

	public void setId(DivisionePK id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}