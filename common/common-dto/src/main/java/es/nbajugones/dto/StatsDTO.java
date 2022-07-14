/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import es.nbajugones.dto.entities.Stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

/**
 * @author iblanco
 */
public class StatsDTO {

	private JugadorDTO jugador;
	private String against;
	private String memberOf;
	private String date;
	private boolean win;
	private int minutos;
	private Integer puntos;
	private Integer asistencias;
	private Integer perdidas;
	private Integer tapones;
	private Integer robos;
	private Integer fga;
	private Integer fgm;
	private Integer tpa;
	private Integer tpm;
	private Integer fta;
	private Integer ftm;
	private Integer rebDef;
	private Integer rebOf;
	private Integer faltas;
	private boolean starter;
	private String posicionHoops;
	private int minutosUsados;
	private int minutosG;
	private int minutosF;
	private int minutosC;
	private double realPuntuacionHoops;
	private int match;

    public StatsDTO() {
        asistencias =0;
        rebDef=0;
        rebOf=0;
        robos=0;
        perdidas=0;
        tapones=0;
        fga=0;
        fgm=0;
        tpa=0;
        tpm=0;
        fta=0;
        ftm=0;
        faltas=0;
    }

    public StatsDTO(String nombreJugador, String posicion, int idJugador, int playerId) {
		this.jugador = new JugadorDTO();
		jugador.setNombre(nombreJugador);
		jugador.setPosicion(posicion);
		jugador.setIdJugador(idJugador);
		jugador.setPlayerId(playerId);
	}

	public StatsDTO(Stats stats, String nombreJugador, String posicion, int idJugador, int playerId) {
		this.jugador = new JugadorDTO();
		jugador.setNombre(nombreJugador);
		jugador.setPosicion(posicion);
		jugador.setIdJugador(idJugador);
		jugador.setPlayerId(playerId);
		this.memberOf = stats.getStatsPK().getIdEquipo();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		if (stats.getSchedule() != null) {
			if (memberOf.equals(stats.getSchedule().getHometeam())) {
				this.against = stats.getSchedule().getAwayteam();
				win = stats.getSchedule().getHomescore().intValue() > stats.getSchedule().getAwayscore().intValue();
			} else {
				this.against = stats.getSchedule().getHometeam();
				win = stats.getSchedule().getHomescore().intValue() < stats.getSchedule().getAwayscore().intValue();
			}
			this.date = sdf.format(stats.getSchedule().getStartdate());
		}

		this.asistencias = stats.getAsistencias();
		this.faltas = stats.getFaltas();
		this.fga = stats.getFga() - stats.getTpa();
		this.fgm = stats.getFgm() - stats.getTpm();
		this.fta = stats.getFta();
		this.ftm = stats.getFtm();
		this.minutos = stats.getMinutos();
		this.perdidas = stats.getPerdidas();
		this.puntos = stats.getPuntos();
		this.rebDef = stats.getRebDef();
		this.rebOf = stats.getRebOf();
		this.starter = stats.getStarter().intValue() == 1;
		this.tapones = stats.getTapones();
		this.tpa = stats.getTpa();
		this.tpm = stats.getTpm();
		this.robos = stats.getRobos();
	}

	public StatsDTO(Stats stats, JugadorDTO jugador) {
		this.jugador = jugador;
		this.memberOf = stats.getStatsPK().getIdEquipo();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		if (stats.getSchedule() != null) {
			if (memberOf.equals(stats.getSchedule().getHometeam())) {
				this.against = stats.getSchedule().getAwayteam();
				win = stats.getSchedule().getHomescore().intValue() > stats.getSchedule().getAwayscore().intValue();
			} else {
				this.against = stats.getSchedule().getHometeam();
				win = stats.getSchedule().getHomescore().intValue() < stats.getSchedule().getAwayscore().intValue();
			}
			this.date = sdf.format(stats.getSchedule().getStartdate());
		}

		this.asistencias = stats.getAsistencias();
		this.faltas = stats.getFaltas();
		this.fga = stats.getFga() - stats.getTpa();
		this.fgm = stats.getFgm() - stats.getTpm();
		this.fta = stats.getFta();
		this.ftm = stats.getFtm();
		this.minutos = stats.getMinutos();
		this.perdidas = stats.getPerdidas();
		this.puntos = stats.getPuntos();
		this.rebDef = stats.getRebDef();
		this.rebOf = stats.getRebOf();
		this.starter = stats.getStarter().intValue() == 1;
		this.tapones = stats.getTapones();
		this.tpa = stats.getTpa();
		this.tpm = stats.getTpm();
		this.robos = stats.getRobos();
	}

	public JugadorDTO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}

	public String getAgainst() {
		return against;
	}

	public void setAgainst(String against) {
		this.against = against;
	}

	public String getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(Integer asistencias) {
		this.asistencias = asistencias;
	}

	public Integer getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(Integer perdidas) {
		this.perdidas = perdidas;
	}

	public Integer getTapones() {
		return tapones;
	}

	public void setTapones(Integer tapones) {
		this.tapones = tapones;
	}

	public Integer getFga() {
		return fga;
	}

	public void setFga(Integer fga) {
		this.fga = fga;
	}

	public Integer getFgm() {
		return fgm;
	}

	public void setFgm(Integer fgm) {
		this.fgm = fgm;
	}

	public Integer getTpa() {
		return tpa;
	}

	public void setTpa(Integer tpa) {
		this.tpa = tpa;
	}

	public Integer getTpm() {
		return tpm;
	}

	public void setTpm(Integer tpm) {
		this.tpm = tpm;
	}

	public Integer getFta() {
		return fta;
	}

	public void setFta(Integer fta) {
		this.fta = fta;
	}

	public Integer getFtm() {
		return ftm;
	}

	public void setFtm(Integer ftm) {
		this.ftm = ftm;
	}

	public Integer getRebDef() {
		return rebDef;
	}

	public void setRebDef(Integer rebDef) {
		this.rebDef = rebDef;
	}

	public Integer getRebOf() {
		return rebOf;
	}

	public void setRebOf(Integer rebOf) {
		this.rebOf = rebOf;
	}

	public int getReb() {
		return (rebDef==null?0:rebDef) + (rebOf==null?0:rebOf);
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	public Integer getRobos() {
		return robos;
	}

	public void setRobos(Integer robos) {
		this.robos = robos;
	}

	public String getPosicionHoops() {
		return posicionHoops;
	}

	public void setPosicionHoops(String posicionHoops) {
		this.posicionHoops = posicionHoops;
	}

	public int getMinutosUsados() {
		return minutosUsados;
	}

	public void setMinutosUsados(int minutosUsados) {
		this.minutosUsados = minutosUsados;
	}

	public int getMinutosG() {
		return minutosG;
	}

	public void setMinutosG(int minutosG) {
		this.minutosG = minutosG;
	}

	public int getMinutosF() {
		return minutosF;
	}

	public void setMinutosF(int minutosF) {
		this.minutosF = minutosF;
	}

	public int getMinutosC() {
		return minutosC;
	}

	public void setMinutosC(int minutosC) {
		this.minutosC = minutosC;
	}

	public double getRealPuntuacionHoops() {
		return realPuntuacionHoops;
	}

	public void setRealPuntuacionHoops(double realPuntuacionHoops) {
		this.realPuntuacionHoops = realPuntuacionHoops;
	}

	public double getPuntuacionHoops() {
		if (minutos > 0) {
			return round((asistencias * 1.5) + rebDef + (rebOf * 1.6) + (tapones * 1.8) + (robos * 1.9) + (ftm * 1.5) + (fgm * 2.6) + (tpm * 3.4)
					- (fta * 0.5) - (fga * 0.6) - (tpa * 0.4) - perdidas - (faltas * 0.2) + (win ? 1 : -1), 2);
		}
		return 0;

	}

	public int getMatch() {
		return match;
	}

	public void setMatch(int match) {
		this.match = match;
	}

	public double getFppm() {
		return (minutos > 0 ? getPuntuacionHoops() / minutos : 0);
	}

	public void addMinutos(int minutos, String posicion) {
		if (posicion.equals("G")) {
			minutosG += minutos;
		} else {
			if (posicion.equals("F")) {
				minutosF += minutos;
			} else {
				minutosC += minutos;
			}
		}
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
