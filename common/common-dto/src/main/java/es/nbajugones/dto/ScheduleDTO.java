/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dto;

import es.nbajugones.dto.entities.Schedule;

/**
 *
 * @author iblanco
 */
public class ScheduleDTO {

	private Integer id;

	private EquipoDTO home;

	private EquipoDTO away;

	private int homeScore, awayScore;

	public ScheduleDTO(Schedule schedule, EquipoDTO home, EquipoDTO away) {
		this.id = schedule.getId();
		this.away = away;
		this.awayScore = schedule.getAwayscore()!=null?schedule.getAwayscore():0;
		this.home = home;
		this.homeScore = schedule.getHomescore()!=null?schedule.getHomescore():0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipoDTO getHome() {
		return home;
	}

	public void setHome(EquipoDTO home) {
		this.home = home;
	}

	public EquipoDTO getAway() {
		return away;
	}

	public void setAway(EquipoDTO away) {
		this.away = away;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public boolean isHomeWinner() {
		return homeScore > awayScore;
	}

	public boolean isAwayWinner() {
		return homeScore < awayScore;
	}

}
