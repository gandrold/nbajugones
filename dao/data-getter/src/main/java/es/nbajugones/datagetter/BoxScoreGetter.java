/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.boxscore.BoxScore;
import java.io.IOException;

/**
 *
 * @author iblanco
 */
public class BoxScoreGetter extends BasicGetter<BoxScore>{

	@Override
	public Class<BoxScore> getGetterClass() {
		return BoxScore.class;
	}

	public BoxScore getBoxScore(String gameId, String token) throws IOException {
		return getResultsWithApache("https://erikberg.com/nba/boxscore/"+gameId+".json", token);
	}

}
