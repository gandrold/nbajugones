/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.test;

import es.nbajugones.dbdao.data.StatsDAO;
import es.nbajugones.dto.SeasonStatsDTO;
import es.nbajugones.dto.entities.Stats;
import es.nbajugones.exception.dbdao.DaoException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author iblanco
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class StatsDAOTest {

	@Autowired
	StatsDAO statsDAO;

	@Test
	public void testAverages(){
		List<SeasonStatsDTO> r = statsDAO.getAverageStats(2706);
		Assert.assertTrue(!r.isEmpty());
		Assert.assertTrue(r.get(0).getJugados().intValue()>0);
	}

	@Test
	public void testSeasonStats(){
		List<SeasonStatsDTO> r = statsDAO.getSeasonStats("2014-15");
		Assert.assertTrue(!r.isEmpty());
		Assert.assertTrue(r.get(0).getNombre() != null);
	}

	@Test
	public void testSeason() throws DaoException{
		List<Stats> r = statsDAO.getSeasonStats("2014-15", 2706);
		Assert.assertTrue(!r.isEmpty());
		Assert.assertTrue(r.get(0).getMinutos()>0);
	}

	@Test
	public void testGetStats() throws DaoException{
		List<Stats> r = statsDAO.getMatchStats(6, 2649, "SAC");
		Assert.assertTrue(!r.isEmpty());
		Assert.assertTrue(r.get(0).getMinutos()>0);
	}

}
