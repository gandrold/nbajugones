/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class DivisionDTO {

	private String nombre;

	private List<HistoricoDTO> datos;

	public DivisionDTO(String nombre, List<HistoricoDTO> datos) {
		this.nombre = nombre;
		this.datos = datos;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<HistoricoDTO> getDatos() {
		return datos;
	}

	public void setDatos(List<HistoricoDTO> datos) {
		this.datos = datos;
	}

	public void ordenar() {
		Collections.sort(datos, new Comparator<HistoricoDTO>() {
			public int compare(HistoricoDTO o1, HistoricoDTO o2) {
				if (o1.getGanados() == o2.getGanados()) {
					return (int) (o2.getMedia() - o1.getMedia());
				} else {
					return o2.getGanados() - o1.getGanados();
				}
			}

		});
	}

}
