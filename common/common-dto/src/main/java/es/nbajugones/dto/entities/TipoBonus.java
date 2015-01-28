package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_bonus database table.
 * 
 */
@Entity
@Table(name="tipo_bonus")
public class TipoBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_BONUS")
	private int idBonus;

	private String nombre;

	public TipoBonus() {
	}

	public int getIdBonus() {
		return this.idBonus;
	}

	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}