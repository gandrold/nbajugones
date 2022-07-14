/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 *
 * @author iblanco
 */
public class SeasonStatsDTO {

	private String season;

	private int idJugador;

	private String nombre;

	private BigInteger jugados;

	private BigDecimal win;
	private BigDecimal lose;

	private BigDecimal minutos;
	private BigDecimal puntos;
	private BigDecimal asistencias;
	private BigDecimal perdidas;
	private BigDecimal tapones;
	private BigDecimal robos;
	private BigDecimal rebDef;
	private BigDecimal rebOf;
	private BigDecimal faltas;
	private BigDecimal fgPerc;
	private BigDecimal tpPerc;
	private BigDecimal ftPerc;
	private BigDecimal fgm;
	private BigDecimal tpm;
	private BigDecimal ftm;
	private BigDecimal fga;
	private BigDecimal tpa;
	private BigDecimal fta;

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public BigInteger getJugados() {
		return jugados;
	}

	public void setJugados(BigInteger jugados) {
		this.jugados = jugados;
	}


	public BigDecimal getMinutos() {
		return minutos;
	}

	public void setMinutos(BigDecimal minutos) {
		this.minutos = minutos;
	}

	public BigDecimal getPuntos() {
		return puntos;
	}

	public void setPuntos(BigDecimal puntos) {
		this.puntos = puntos;
	}

	public BigDecimal getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(BigDecimal asistencias) {
		this.asistencias = asistencias;
	}

	public BigDecimal getPerdidas() {
		return perdidas;
	}

	public void setPerdidas(BigDecimal perdidas) {
		this.perdidas = perdidas;
	}

	public BigDecimal getTapones() {
		return tapones;
	}

	public void setTapones(BigDecimal tapones) {
		this.tapones = tapones;
	}

	public BigDecimal getRebDef() {
		return rebDef;
	}

	public void setRebDef(BigDecimal rebDef) {
		this.rebDef = rebDef;
	}

	public BigDecimal getRebOf() {
		return rebOf;
	}

	public void setRebOf(BigDecimal rebOf) {
		this.rebOf = rebOf;
	}

	public BigDecimal getFaltas() {
		return faltas;
	}

	public void setFaltas(BigDecimal faltas) {
		this.faltas = faltas;
	}

	public BigDecimal getRobos() {
		return robos;
	}

	public void setRobos(BigDecimal robos) {
		this.robos = robos;
	}

	public BigDecimal getFgPerc() {
		return fgPerc;
	}

	public void setFgPerc(BigDecimal fgPerc) {
		this.fgPerc = fgPerc;
	}

	public BigDecimal getTpPerc() {
		return tpPerc;
	}

	public void setTpPerc(BigDecimal tpPerc) {
		this.tpPerc = tpPerc;
	}

	public BigDecimal getFtPerc() {
		return ftPerc;
	}

	public void setFtPerc(BigDecimal ftPerc) {
		this.ftPerc = ftPerc;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getFgm() {
		return fgm;
	}

	public void setFgm(BigDecimal fgm) {
		this.fgm = fgm;
	}

	public BigDecimal getTpm() {
		return tpm;
	}

	public void setTpm(BigDecimal tpm) {
		this.tpm = tpm;
	}

	public BigDecimal getFtm() {
		return ftm;
	}

	public void setFtm(BigDecimal ftm) {
		this.ftm = ftm;
	}

	public BigDecimal getFga() {
		return fga;
	}

	public void setFga(BigDecimal fga) {
		this.fga = fga;
	}

	public BigDecimal getTpa() {
		return tpa;
	}

	public void setTpa(BigDecimal tpa) {
		this.tpa = tpa;
	}

	public BigDecimal getFta() {
		return fta;
	}

	public void setFta(BigDecimal fta) {
		this.fta = fta;
	}

	public BigDecimal getWin() {
		return win;
	}

	public void setWin(BigDecimal win) {
		this.win = win;
	}

	public BigDecimal getLose() {
		return lose;
	}

	public void setLose(BigDecimal lose) {
		this.lose = lose;
	}



	public double getPuntuacionHoops() {
		return (asistencias.doubleValue() * 1.5) + rebDef.doubleValue() + (rebOf.doubleValue() * 1.6) + (tapones.doubleValue() * 1.8) + (robos.doubleValue() * 1.9) +
				(ftm.doubleValue() * 1.5) + (fgm.doubleValue() * 2.6) + (tpm.doubleValue() * 3.4) -
				(fta.doubleValue() * 0.5) - (fga.doubleValue() * 0.6) - (tpa.doubleValue() * 0.4) - perdidas.doubleValue() - (faltas.doubleValue() * 0.2);
	}

	public double getFppm() {
		return getPuntuacionHoops()/minutos.doubleValue();
	}







}
