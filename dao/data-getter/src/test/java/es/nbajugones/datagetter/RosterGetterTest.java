/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import es.nbajugones.datagetter.beans.roster.Roster;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iblanco
 */
public class RosterGetterTest {

	RosterGetter rosterGetter;

	public RosterGetterTest() {
	}

	@Before
	public void setUp() {
		rosterGetter = new RosterGetter();
	}

	//@Test
	public void test(){
		try {
			Roster roster = rosterGetter.getRoster("detroit-pistons", "");
			assertTrue(!roster.getPlayers().isEmpty());
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
