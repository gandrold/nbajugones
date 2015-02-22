package es.nbajugones.dto;

import es.nbajugones.dto.entities.Jugadores;

public class JugadorDTO {
	private Integer idJugador;
	private Integer activo;
	private String years;
	private String cortadoPor;
	private Integer idHoops;
	private String jugador;
	private Integer jugados;
	private Double minutos;
	private String obs;
	private String posicion;
	private Double promedio;
	private Double puntos;
	private Double salario;
	private String url;

	public JugadorDTO(Jugadores jugador) {
		this.activo = jugador.getActivo();
		this.cortadoPor = jugador.getCortadoPor();
		this.idHoops = jugador.getIdHoops();
		this.idJugador = jugador.getIdJugador();
		this.jugador = jugador.getJugador();
		this.jugados = jugador.getJugados();
		this.minutos = jugador.getMinutos();
		this.obs = jugador.getObs();
		this.posicion = jugador.getPosicion();
		this.promedio = jugador.getPromedio();
		this.puntos = jugador.getPuntos();
		this.salario = jugador.getSalario();
		this.url = jugador.getUrl();
		this.years = jugador.getYears();
				
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

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
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
}