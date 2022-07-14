/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.roster;

import es.nbajugones.datagetter.beans.commons.Team;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class Roster {

	@JsonProperty("team")
	private Team team;

	@JsonProperty("players")
	private List<Player> players;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	

}
