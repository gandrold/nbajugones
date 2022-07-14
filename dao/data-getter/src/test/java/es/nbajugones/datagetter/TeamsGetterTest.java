/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.commons.Team;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iblanco
 */
public class TeamsGetterTest {

	TeamsGetter teamsGetter;

	public TeamsGetterTest() {
	}

	@Before
	public void setUp() {
		teamsGetter = new TeamsGetter();
	}

	//@Test
	public void test(){
		try {
			List<Team> teams = teamsGetter.getTeams();
			assertTrue(!teams.isEmpty());
			assertTrue(!teams.get(0).getAbbreviation().equals(""));
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
