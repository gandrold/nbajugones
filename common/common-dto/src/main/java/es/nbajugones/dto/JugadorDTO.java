package es.nbajugones.dto;

import es.nbajugones.dto.entities.Jugadores;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JugadorDTO implements Comparable<JugadorDTO> {

	private Integer idJugador;
	private Integer activo;
	private String years;
	private String cortadoPor;
	private Integer idHoops;
	private String nombre;
	private Integer jugados;
	private Double minutos;
	private String obs;
	private String posicion;
	private Double promedio;
	private Double puntos;
	private Double salario;
	private String url;
	private String equipo;
	private Integer playerId;

	public JugadorDTO(Jugadores jugador) {
		this.activo = jugador.getActivo();
		this.cortadoPor = jugador.getCortadopor();
		this.idJugador = jugador.getIdjugador();
		this.nombre = jugador.getJugador();
		this.jugados = jugador.getJugados();
		this.minutos = jugador.getMinutos();
		Date fecha = jugador.getFecha();
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
		String obs = "";
		if (jugador.getStatus() != null) {
			switch (jugador.getStatus()) {
				case 1:
					long diff = today.getTime() - fecha.getTime();
					long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					if (days < 5) {
						Calendar c = Calendar.getInstance();
						c.setTime(fecha);
						c.add(Calendar.DATE, 5);
						obs = String.format("No movible antes del %s (Si se traspasa, se ha de mantener 15 dias en destino)", sdf.format(c.getTime()));
					} else {
						if (5 <= days && days < 15) {
							obs = "Si se traspasa, se ha de mantener 15 dias en destino";
						}
					}
					break;
				case 2:
					diff = today.getTime() - fecha.getTime();
					days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					if (days < 15) {
						Calendar c = Calendar.getInstance();
						c.setTime(fecha);
						c.add(Calendar.DATE, 15);
						obs = String.format("No movible antes del %s", sdf.format(c.getTime()));
					}
					break;
				case 3:
					obs = "Jugador no traspasable.";
					break;
				default:
					break;
			}
		}
		if (jugador.getLesionado() != null && jugador.getLesionado() == 1){
			obs += (obs.equals("")?"":". ") + "Lesionado de larga duracion";
		}
		this.posicion = jugador.getPosicion();
		this.promedio = jugador.getPromedio();
		this.puntos = jugador.getPuntos();
		if (jugador.getRenovar()!= null) {
			if ( jugador.getRenovar() == 1) {
				this.salario = (double) 0;
				obs = "FA";
			} else {
				if (jugador.getRenovar() == 2){
					obs = "Jugador renovado. No puede ser cortado durante regularización";
				}
				if (jugador.getRenovar() == 3){
					obs += "Jugador renovado. No movible hasta el 10-10 a las 22:00";
				}
				this.salario = jugador.getSalario();
			}
		} else {
			this.salario = jugador.getSalario();
		}
		this.obs = obs;
		this.years = jugador.getYears();
		this.equipo = jugador.getEquipo();
		this.playerId = jugador.getPlayerid();

	}

	public JugadorDTO() {

	}

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getCortadoPor() {
		return cortadoPor;
	}

	public void setCortadoPor(String cortadoPor) {
		this.cortadoPor = cortadoPor;
	}

	public Integer getIdHoops() {
		return idHoops;
	}

	public void setIdHoops(Integer idHoops) {
		this.idHoops = idHoops;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String jugador) {
		this.nombre = jugador;
	}

	public Integer getJugados() {
		return jugados;
	}

	public void setJugados(Integer jugados) {
		this.jugados = jugados;
	}

	public Double getMinutos() {
		return minutos;
	}

	public void setMinutos(Double minutos) {
		this.minutos = minutos;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Double getPromedio() {
		return promedio;
	}

	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}

	public Double getPuntos() {
		return puntos;
	}

	public void setPuntos(Double puntos) {
		this.puntos = puntos;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getFirstName(){
		return nombre.substring(0, nombre.indexOf(" "));
	}
	public String getLastName(){
		return nombre.substring(nombre.indexOf(" ")+1);
	}

	public String getNombreFoto() {
		return nombre.toLowerCase().replaceAll(" ", "_").replaceAll("\\.", "");
	}

	public String getNombreLink() {
		return nombre.toLowerCase().replaceAll(" ", "-").replaceAll("\\.", "");
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public int compareTo(JugadorDTO o) {
		int i = o.posicion.compareTo(posicion);
		if (posicion.substring(0, 1).equals(o.posicion.substring(0, 1))) {
			if (puntos == null) {
				if (o.puntos == null) {
					return nombre.compareTo(o.nombre);
				} else {
					return 1;
				}
			} else {
				if (o.puntos == null) {
					return -1;
				} else {
					return o.puntos.compareTo(puntos);
				}
			}

		} else {
			if (i == 0) {
				return o.puntos.compareTo(puntos);
			} else {
				return i;
			}
		}
	}
}
