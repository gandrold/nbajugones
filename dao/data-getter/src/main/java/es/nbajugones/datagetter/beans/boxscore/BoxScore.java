/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.boxscore;

import es.nbajugones.datagetter.beans.commons.Team;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class BoxScore {

	@JsonProperty("away_team")
	private Team awayTeam;

	@JsonProperty("home_team")
	private Team homeTeam;

	@JsonProperty("away_period_scores")
	private List<Integer> awayPeriodScores;
	@JsonProperty("home_period_scores")
	private List<Integer> homePeriodScores;

	@JsonProperty("away_stats")
	private List<PlayerStats> awayStats;
	@JsonProperty("home_stats")
	private List<PlayerStats> homeStats;

	@JsonProperty("officials")
	private List<Official> officials;

	@JsonProperty("event_information")
	private Info info;

	@JsonProperty("away_totals")
	private PlayerStats awayTotals;
	@JsonProperty("home_totals")
	private PlayerStats homeTotals;

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public List<Integer> getAwayPeriodScores() {
		return awayPeriodScores;
	}

	public void setAwayPeriodScores(List<Integer> awayPeriodScores) {
		this.awayPeriodScores = awayPeriodScores;
	}

	public List<Integer> getHomePeriodScores() {
		return homePeriodScores;
	}

	public void setHomePeriodScores(List<Integer> homePeriodScores) {
		this.homePeriodScores = homePeriodScores;
	}

	public List<PlayerStats> getAwayStats() {
		return awayStats;
	}

	public void setAwayStats(List<PlayerStats> awayStats) {
		this.awayStats = awayStats;
	}

	public List<PlayerStats> getHomeStats() {
		return homeStats;
	}

	public void setHomeStats(List<PlayerStats> homeStats) {
		this.homeStats = homeStats;
	}

	public List<Official> getOfficials() {
		return officials;
	}

	public void setOfficials(List<Official> officials) {
		this.officials = officials;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public PlayerStats getAwayTotals() {
		return awayTotals;
	}

	public void setAwayTotals(PlayerStats awayTotals) {
		this.awayTotals = awayTotals;
	}

	public PlayerStats getHomeTotals() {
		return homeTotals;
	}

	public void setHomeTotals(PlayerStats homeTotals) {
		this.homeTotals = homeTotals;
	}

	

}
