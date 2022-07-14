/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.roster.Roster;
import java.io.IOException;

/**
 *
 * @author iblanco
 */
public class RosterGetter extends BasicGetter<Roster>{

	@Override
	public Class<Roster> getGetterClass() {
		return Roster.class;
	}

	public Roster getRoster(String team, String token) throws IOException {
		return getResultsWithApache("https://erikberg.com/nba/roster/"+team+".json", token);
	}

}
