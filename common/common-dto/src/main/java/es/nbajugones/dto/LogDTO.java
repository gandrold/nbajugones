package es.nbajugones.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.nbajugones.dto.entities.Log;


public class LogDTO implements Comparable<LogDTO>{

	private String idEquipo;
	
	private Date fecha;

	private String texto;
	
	public LogDTO(Log log){
		idEquipo = log.getIdEquipo();
		String tempText = log.getTexto();
		String[] tokens = tempText.split(" - ");
		SimpleDateFormat sdf = new SimpleDateFormat ("dd-MMM-yyyy HH:mm:SS");
		try {
			fecha = sdf.parse(tokens[0]);
			texto = tokens[1];
		} catch (ParseException e) {
			sdf = new SimpleDateFormat ("dd-MMM-yyyy HH:mm:SS", Locale.ENGLISH);
			
			try {
			fecha = sdf.parse(tokens[0]);
			texto = tokens[1];
			} catch (ParseException e2) {
				sdf = new SimpleDateFormat ("MMM dd, yyyy HH:mm:SS a");
				try {
					fecha = sdf.parse(tokens[0]);
					texto = tokens[1];
					} catch (ParseException e3) {
						sdf = new SimpleDateFormat ("MMM dd, yyyy HH:mm:SS a", Locale.ENGLISH);
						try {
							fecha = sdf.parse(tokens[0]);
							texto = tokens[1];
							} catch (ParseException e4) {
								e4.printStackTrace();
								texto=tempText;
							}
					}
			}
			
		}
		
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int compareTo(LogDTO o) {
		// TODO Auto-generated method stub
		return o.fecha.compareTo(fecha);
	}
	
	
}
