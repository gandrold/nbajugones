package es.nbajugones.dto.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the log database table.
 * 
 */
@Entity
@Table(name="log")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID_ACCION")
	private int idAccion;

	@Column(name="ID_EQUIPO")
	private String idEquipo;

	@Lob
	private String texto;

	public Log() {
	}

	public int getIdAccion() {
		return this.idAccion;
	}

	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}

	public String getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}