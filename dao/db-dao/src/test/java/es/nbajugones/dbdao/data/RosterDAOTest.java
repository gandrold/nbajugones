package es.nbajugones.dbdao.data;

import es.nbajugones.dto.entities.Roster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iblanco on 18/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional


public class RosterDAOTest {

	@Autowired
	RosterDAO rosterDAO;

	@Test
	public void getTeamGame() throws Exception {
		assertEquals(12, rosterDAO.getTeamGame("2016-17","MIA", 6).size());
	}

	@Test
	public void freeze() throws Exception{
		assertEquals(0, rosterDAO.getTeamGame("2016-17","MIA", 7).size());
		List<Roster> r = rosterDAO.freeze("2016-17","MIA", 6);
		assertEquals(12,r.size());
		assertEquals(7, r.get(0).getRosterPK().getGame());
	}

	@Test
	public void saveRoster() throws Exception {
		List<Roster> roster = rosterDAO.getTeamGame("2016-17","MIA", 6);
		roster.get(0).setSpot(2);
		roster.get(1).setSpot(1);
		List<Roster> newRoster = rosterDAO.saveRoster(roster);
		assertEquals(roster.get(0).getSpot(), newRoster.get(0).getSpot());
		assertEquals(12, rosterDAO.getTeamGame("2016-17","MIA", 6).size());
	}

}