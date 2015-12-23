/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class ConferenciaDTO {

	private String nombre;

	private List<DivisionDTO> divisiones;

	public ConferenciaDTO(String nombre, List<DivisionDTO> divisiones) {
		this.nombre = nombre;
		this.divisiones = divisiones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DivisionDTO> getDivisiones() {
		return divisiones;
	}

	public void setDivisiones(List<DivisionDTO> divisiones) {
		this.divisiones = divisiones;
	}

	public void addHistorico(HistoricoDTO historico, String nombreDivision){
		DivisionDTO division = null;
		for (DivisionDTO d: divisiones){
			if (d.getNombre().equals(nombreDivision)){
				division = d;
				break;
			}
		}
		if (division == null){
			division = new DivisionDTO(nombreDivision, new ArrayList<HistoricoDTO>());
			divisiones.add(division);
		}
		division.getDatos().add(historico);
	}

	public void ordenar(){
		Collections.sort(divisiones, new Comparator<DivisionDTO>(){
			public int compare(DivisionDTO o1, DivisionDTO o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}

		});
		for (DivisionDTO d: divisiones){
			d.ordenar();
		}
	}


}
