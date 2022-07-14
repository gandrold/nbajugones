/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.events;

import es.nbajugones.datagetter.beans.commons.Site;
import es.nbajugones.datagetter.beans.commons.Team;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class Event {

	@JsonProperty("event_id")
	private String eventId;
	@JsonProperty("start_date_time")
	private Date startDateTime;
	@JsonProperty("event_status")
	private String status;
	@JsonProperty("sport")
	private String sport;
	@JsonProperty("season_type")
	private String seasonType;
	@JsonProperty("away_period_scores")
	private List<Integer> awayPeriodScores;
	@JsonProperty("home_period_scores")
	private List<Integer> homePeriodScores;
	@JsonProperty("home_team")
	private Team homeTeam;
	@JsonProperty("away_team")
	private Team awayTeam;
	@JsonProperty("home_points_scored")
	private Integer homeScore;
	@JsonProperty("away_points_scored")
	private Integer awayScore;
	@JsonProperty("site")
	private Site site;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
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

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}




}
