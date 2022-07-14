package es.nbajugones.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportDTO {

	private List<String> jugadoresNuevos;
	
	private List<DerechoDTO> derechos;

	private List<String> sospechosos;

	public ExportDTO(){
		jugadoresNuevos = new ArrayList<>();
		derechos = new ArrayList<>();
		sospechosos = new ArrayList<>();
	}
	
	public List<String> getJugadoresNuevos() {
		return jugadoresNuevos;
	}

	public void setJugadoresNuevos(List<String> jugadoresNuevos) {
		this.jugadoresNuevos = jugadoresNuevos;
	}

	public List<DerechoDTO> getDerechos() {
		return derechos;
	}

	public void setDerechos(List<DerechoDTO> derechos) {
		this.derechos = derechos;
	}

	public List<String> getSospechosos() {
		return sospechosos;
	}

	public void setSospechosos(List<String> sospechosos) {
		this.sospechosos = sospechosos;
	}

	public void sortDerechos(){
		Collections.sort(derechos, new Comparator<DerechoDTO>() {
			public int compare(DerechoDTO o1, DerechoDTO o2) {
				if (o1.getEquipo().equals(o2.getEquipo())){
					return o1.getJugador().compareTo(o2.getJugador());
				}
				return o1.getEquipo().compareTo(o2.getEquipo());
			}
		});
	}
	
	public boolean isOk(){
		return derechos.isEmpty() && jugadoresNuevos.isEmpty();
	}
	
	
}
