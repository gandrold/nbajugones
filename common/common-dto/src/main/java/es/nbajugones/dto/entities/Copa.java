package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.CopaPK;


/**
 * The persistent class for the copa database table.
 * 
 */
@Entity
public class Copa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CopaPK id;

	@Column(name="id_equipo_casa")
	private String idEquipoCasa;

	@Column(name="id_equipo_fuera")
	private String idEquipoFuera;

	@Column(name="puntos_casa")
	private Double puntosCasa;

	@Column(name="puntos_fuera")
	private Double puntosFuera;

	private String url;

	public Copa() {
	}

	public CopaPK getId() {
		return this.id;
	}

	public void setId(CopaPK id) {
		this.id = id;
	}

	public String getIdEquipoCasa() {
		return this.idEquipoCasa;
	}

	public void setIdEquipoCasa(String idEquipoCasa) {
		this.idEquipoCasa = idEquipoCasa;
	}

	public String getIdEquipoFuera() {
		return this.idEquipoFuera;
	}

	public void setIdEquipoFuera(String idEquipoFuera) {
		this.idEquipoFuera = idEquipoFuera;
	}

	public Double getPuntosCasa() {
		return this.puntosCasa;
	}

	public void setPuntosCasa(Double puntosCasa) {
		this.puntosCasa = puntosCasa;
	}

	public Double getPuntosFuera() {
		return this.puntosFuera;
	}

	public void setPuntosFuera(Double puntosFuera) {
		this.puntosFuera = puntosFuera;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}