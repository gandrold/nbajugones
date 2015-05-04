package es.nbajugones.dto;

import es.nbajugones.dto.entities.Derecho;


public class DerechoDTO {

	private String jugador;
	
	private Integer anoEleccion;

	private Integer anos;

	private String posicion;

	private Double salario;
	
	public DerechoDTO(Derecho derecho){
		this.anoEleccion = derecho.getAnoEleccion();
		this.anos = derecho.getAnos();
		this.jugador = derecho.getId().getJugador();
		this.posicion = derecho.getPosicion();
		this.salario = derecho.getSalario();
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public Integer getAnoEleccion() {
		return anoEleccion;
	}

	public void setAnoEleccion(Integer anoEleccion) {
		this.anoEleccion = anoEleccion;
	}

	public Integer getAnos() {
		return anos;
	}

	public void setAnos(Integer anos) {
		this.anos = anos;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
	
}
