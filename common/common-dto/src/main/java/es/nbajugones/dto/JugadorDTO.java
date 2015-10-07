package es.nbajugones.dto;

import es.nbajugones.dto.entities.Jugadores;

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

	public JugadorDTO(Jugadores jugador) {
		this.activo = jugador.getActivo();
		this.cortadoPor = jugador.getCortadoPor();
		this.idHoops = jugador.getIdHoops();
		this.idJugador = jugador.getIdJugador();
		this.nombre = jugador.getJugador();
		this.jugados = jugador.getJugados();
		this.minutos = jugador.getMinutos();
		this.obs = jugador.getObs();
		this.posicion = jugador.getPosicion();
		this.promedio = jugador.getPromedio();
		this.puntos = jugador.getPuntos();
		if ("FA".equals(obs)) {
			this.salario = (double) 0;
		} else {
			this.salario = jugador.getSalario();
		}
		this.url = jugador.getUrl();
		this.years = jugador.getYears();
		this.equipo = jugador.getEquipo();

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

	public String getNombreFoto() {
		return nombre.toLowerCase().replaceAll(" ", "_").replaceAll("\\.", "");
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