/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.results.ResultEvent;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author iblanco
 */
public class ResultsGetter extends BasicGetter<ResultEvent>{

	@Override
	public Class<ResultEvent> getGetterClass() {
		return ResultEvent.class;
	}

	public List<ResultEvent> getCurrentSeasonResults(String team, String token) throws IOException{
		return getResults(team, null, token);
	}

	public List<ResultEvent> getResults(String team, String season, String token) throws IOException{
		return getResults("https://erikberg.com/nba/results/"+team+".json"+(season!=null?"?season="+season:""), token);
	}

}
