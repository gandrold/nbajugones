/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.commons.Team;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class TeamsGetter extends BasicGetter<Team>{

	@Override
	public Class<Team> getGetterClass() {
		return Team.class;
	}

	public List<Team> getTeams() throws IOException{
		return getResults("https://erikberg.com/nba/teams.json", null);
	}

}
