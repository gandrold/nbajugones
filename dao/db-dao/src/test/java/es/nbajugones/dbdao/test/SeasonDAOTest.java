/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.dbdao.test;

import es.nbajugones.dbdao.data.SeasonDAO;
import es.nbajugones.dto.entities.Season;
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
public class SeasonDAOTest {

	@Autowired
	SeasonDAO seasonDAO;

	@Test
	public void test(){
		Season s = seasonDAO.getFirstUnfinishedSeason();
		Assert.assertTrue(s == null);
	}

}
