package es.nbajugones.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.nbajugones.dto.entities.Log;

public class LogDTO implements Comparable<LogDTO> {

	private String idEquipo;

	private Date fecha;

	private String texto;
	
	public static final String[] DATE_FORMATS ={"dd-MMM-yyyy HH:mm:SS", "MMM dd, yyyy HH:mm:SS a", "dd-MMM-yyyy HH:mm"};
	

	public LogDTO(Log log) {
		idEquipo = log.getIdEquipo();
		String tempText = log.getTexto();
		String[] tokens = tempText.split(" - ");
		for (int i=0;i<DATE_FORMATS.length && texto == null;i++){
			parseLog(DATE_FORMATS[i], tokens);			
		}
		if (texto == null){
			texto=tempText;
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
		if (fecha!=null & o.fecha!=null){
			return o.fecha.compareTo(fecha);
		} else {
			return -1;
		}
	}

	private void parseLog(String format, String[] tokens) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf = new SimpleDateFormat(format, new Locale("es", "ES"));
			fecha = sdf.parse(tokens[0]);
			texto = tokens[1];
		} catch (ParseException e) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			try {
				fecha = sdf.parse(tokens[0]);
				texto = tokens[1];
			} catch (ParseException e4) {
			}
		}
	}

}
