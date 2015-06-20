package es.nbajugones.dto.entities;

import java.io.Serializable;
import javax.persistence.*;

import es.nbajugones.dto.entities.pk.RondasDraftPK;


/**
 * The persistent class for the rondas_draft database table.
 * 
 */
@Entity
@Table(name="rondas_draft")
public class RondasDraft implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RondasDraftPK id;

	@Column(name="duracion")
	private String duracion;

	@Column(name="ID_EQUIPO_PROP")
	private String idEquipoProp;

	@Column(name="ID_JUGADOR")
	private String idJugador;

	@Column(name="jugador")
	private String jugador;

	@Column(name="letra")
	private String letra;

	@Column(name="salario")
	private String salario;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EQUIPO",insertable=false, updatable=false)
	private Equipo equipoRonda;
	
	public Equipo getEquipoRonda() {
		return equipoRonda;
	}

	public void setEquipoRonda(Equipo equipoRonda) {
		this.equipoRonda = equipoRonda;
	}

	public RondasDraft() {
	}

	public RondasDraftPK getId() {
		return this.id;
	}

	public void setId(RondasDraftPK id) {
		this.id = id;
	}

	public String getDuracion() {
		return this.duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getIdEquipoProp() {
		return this.idEquipoProp;
	}

	public void setIdEquipoProp(String idEquipoProp) {
		this.idEquipoProp = idEquipoProp;
	}

	public String getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

	public String getJugador() {
		return this.jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "RondasDraft [id=" + id + ", duracion=" + duracion
				+ ", idEquipoProp=" + idEquipoProp + ", idJugador=" + idJugador
				+ ", jugador=" + jugador + ", letra=" + letra + ", salario="
				+ salario + "]";
	}
	
	

}