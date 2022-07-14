/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.results;

import es.nbajugones.datagetter.beans.commons.Site;
import es.nbajugones.datagetter.beans.commons.Team;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class ResultEvent {

	@JsonProperty("event_id")
	private String eventId;
	@JsonProperty("event_start_date_time")
	private Date startDateTime;
	@JsonProperty("event_status")
	private String status;
	@JsonProperty("event_season_type")
	private String seasonType;
	@JsonProperty("team_event_location_type")
	private String location;
	@JsonProperty("team_event_result")
	private String result;
	@JsonProperty("team_points_scored")
	private Integer pointsScored;
	@JsonProperty("team_event_number_in_season")
	private Integer eventNumber;
	@JsonProperty("team_events_won")
	private Integer eventsWon;
	@JsonProperty("team_events_lost")
	private Integer eventsLost;
	@JsonProperty("opponent_points_scored")
	private Integer opponentPointsScored;
	@JsonProperty("opponent_events_won")
	private Integer opponentEventsWon;
	@JsonProperty("opponent_events_lost")
	private Integer opponentEventsLost;
	@JsonProperty("team")
	private Team team;
	@JsonProperty("opponent")
	private Team opponent;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getPointsScored() {
		return pointsScored;
	}

	public void setPointsScored(Integer pointsScored) {
		this.pointsScored = pointsScored;
	}

	public Integer getEventsWon() {
		return eventsWon;
	}

	public void setEventsWon(Integer eventsWon) {
		this.eventsWon = eventsWon;
	}

	public Integer getEventsLost() {
		return eventsLost;
	}

	public void setEventsLost(Integer eventsLost) {
		this.eventsLost = eventsLost;
	}

	public Integer getOpponentPointsScored() {
		return opponentPointsScored;
	}

	public void setOpponentPointsScored(Integer opponentPointsScored) {
		this.opponentPointsScored = opponentPointsScored;
	}

	public Integer getOpponentEventsWon() {
		return opponentEventsWon;
	}

	public void setOpponentEventsWon(Integer opponentEventsWon) {
		this.opponentEventsWon = opponentEventsWon;
	}

	public Integer getOpponentEventsLost() {
		return opponentEventsLost;
	}

	public void setOpponentEventsLost(Integer opponentEventsLost) {
		this.opponentEventsLost = opponentEventsLost;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getOpponent() {
		return opponent;
	}

	public void setOpponent(Team opponent) {
		this.opponent = opponent;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	

}
