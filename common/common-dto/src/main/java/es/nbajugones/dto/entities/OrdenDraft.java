package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.OrdenDraftPK;


/**
 * The persistent class for the orden_draft database table.
 * 
 */
@Entity
@Table(name="orden_draft")
public class OrdenDraft implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdenDraftPK id;

	private int posicion;

	public OrdenDraft() {
	}

	public OrdenDraftPK getId() {
		return this.id;
	}

	public void setId(OrdenDraftPK id) {
		this.id = id;
	}

	public int getPosicion() {
		return this.posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}