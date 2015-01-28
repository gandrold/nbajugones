package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.PlantillaPK;


/**
 * The persistent class for the plantillas database table.
 * 
 */
@Entity
@Table(name="plantillas")
public class Plantilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlantillaPK id;

	public Plantilla() {
	}

	public PlantillaPK getId() {
		return this.id;
	}

	public void setId(PlantillaPK id) {
		this.id = id;
	}

}