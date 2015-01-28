package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipos_sanciones database table.
 * 
 */
@Entity
@Table(name="tipos_sanciones")
public class TiposSancione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SANCION")
	private int idSancion;

	@Column(name="NOMBRE_SANCION")
	private String nombreSancion;

	public TiposSancione() {
	}

	public int getIdSancion() {
		return this.idSancion;
	}

	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}

	public String getNombreSancion() {
		return this.nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

}