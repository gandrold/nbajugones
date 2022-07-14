/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.boxscore.BoxScore;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iblanco
 */
public class BoxScoreGetterTest {

	BoxScoreGetter boxScoreGetter;

	public BoxScoreGetterTest() {
	}

	@Before
	public void setUp() {
		boxScoreGetter = new BoxScoreGetter();
	}

	//@Test
	public void test(){
		try {
			BoxScore boxScore = boxScoreGetter.getBoxScore("20170226-san-antonio-spurs-at-los-angeles-lakers", "ee3f9524-1696-4309-be27-799c3e37740b");
			assertTrue(!boxScore.getAwayStats().isEmpty());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
