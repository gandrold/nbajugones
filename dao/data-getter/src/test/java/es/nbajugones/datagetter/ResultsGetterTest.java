/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.results.ResultEvent;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iblanco
 */
public class ResultsGetterTest {

	ResultsGetter resultsGetter;

	public ResultsGetterTest() {
	}

	@Before
	public void setUp() {
		resultsGetter = new ResultsGetter();
	}

	//@Test
	public void test(){
		try {
			List<ResultEvent> results = resultsGetter.getCurrentSeasonResults("brooklyn-nets", "");
			assertTrue(!results.isEmpty());
			assertTrue(!results.get(0).getOpponent().getAbbreviation().equals(""));
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
