/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

/**
 *
 * @author iblanco
 */
public class ReduccionDTO {

	private String nombre;

	private String contrato;

	private String equipo;

	private int partidosActual;

	private int partidosPasada;

	private double minutosActual;

	private double minutosPasada;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public int getPartidosActual() {
		return partidosActual;
	}

	public void setPartidosActual(int partidosActual) {
		this.partidosActual = partidosActual;
	}

	public int getPartidosPasada() {
		return partidosPasada;
	}

	public void setPartidosPasada(int partidosPasada) {
		this.partidosPasada = partidosPasada;
	}

	public double getMinutosActual() {
		return minutosActual;
	}

	public void setMinutosActual(double minutosActual) {
		this.minutosActual = minutosActual;
	}

	public double getMinutosPasada() {
		return minutosPasada;
	}

	public void setMinutosPasada(double minutosPasada) {
		this.minutosPasada = minutosPasada;
	}

	public double getPercPartidos(){
		return ((double)partidosActual) / ((double)partidosPasada);
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}



	public double getFactor() {
		double perc = getPercPartidos();
		if (perc < 0.25) {
			return 0.25;
		} else {
			if (perc < 0.5) {
				return 0.5;
			} else {
				if (perc < 0.75) {
					return 0.75;
				} else {
					return 1;
				}
			}
		}
	}

	public double getResultado(){
		return minutosPasada - minutosActual*getFactor();
	}

}
