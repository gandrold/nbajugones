/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.boxscore;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class PlayerStats {

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("position")
	private String position;

	@JsonProperty("team_abbreviation")
	private String team;

	@JsonProperty("minutes")
	private Integer minutes;

	@JsonProperty("is_starter")
	private boolean starter;

	@JsonProperty("points")
	private Integer points;

	@JsonProperty("assists")
	private Integer assists;

	@JsonProperty("turnovers")
	private Integer turnovers;

	@JsonProperty("steals")
	private Integer steals;

	@JsonProperty("blocks")
	private Integer blocks;

	@JsonProperty("field_goals_attempted")
	private Integer fga;

	@JsonProperty("field_goals_made")
	private Integer fgm;

	@JsonProperty("three_point_field_goals_attempted")
	private Integer tpa;

	@JsonProperty("three_point_field_goals_made")
	private Integer tpm;

	@JsonProperty("free_throws_attempted")
	private Integer fta;

	@JsonProperty("free_throws_made")
	private Integer ftm;

	@JsonProperty("defensive_rebounds")
	private Integer defensiveRebounds;

	@JsonProperty("offensive_rebounds")
	private Integer offensiveRebounds;

	@JsonProperty("rebounds")
	private Integer rebounds;

	@JsonProperty("personal_fouls")
	private Integer pf;

	@JsonProperty("field_goal_percentage")
	private Float fgPerc;

	@JsonProperty("three_point_percentage")
	private Float tpPerc;

	@JsonProperty("free_throw_percentage")
	private Float ftPerc;

	@JsonProperty("field_goal_percentage_string")
	private String fgPercString;

	@JsonProperty("three_point_field_goal_percentage_string")
	private String tpPercString;

	@JsonProperty("free_throw_percentage_string")
	private String ftPercString;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	public Integer getTurnovers() {
		return turnovers;
	}

	public void setTurnovers(Integer turnovers) {
		this.turnovers = turnovers;
	}

	public Integer getSteals() {
		return steals;
	}

	public void setSteals(Integer steals) {
		this.steals = steals;
	}

	public Integer getBlocks() {
		return blocks;
	}

	public void setBlocks(Integer blocks) {
		this.blocks = blocks;
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

	public Integer getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public void setDefensiveRebounds(Integer defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}

	public Integer getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public void setOffensiveRebounds(Integer offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public Integer getRebounds() {
		return rebounds;
	}

	public void setRebounds(Integer rebounds) {
		this.rebounds = rebounds;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Float getFgPerc() {
		return fgPerc;
	}

	public void setFgPerc(Float fgPerc) {
		this.fgPerc = fgPerc;
	}

	public Float getTpPerc() {
		return tpPerc;
	}

	public void setTpPerc(Float tpPerc) {
		this.tpPerc = tpPerc;
	}

	public Float getFtPerc() {
		return ftPerc;
	}

	public void setFtPerc(Float ftPerc) {
		this.ftPerc = ftPerc;
	}

	public String getFgPercString() {
		return fgPercString;
	}

	public void setFgPercString(String fgPercString) {
		this.fgPercString = fgPercString;
	}

	public String getTpPercString() {
		return tpPercString;
	}

	public void setTpPercString(String tpPercString) {
		this.tpPercString = tpPercString;
	}

	public String getFtPercString() {
		return ftPercString;
	}

	public void setFtPercString(String ftPercString) {
		this.ftPercString = ftPercString;
	}


}
