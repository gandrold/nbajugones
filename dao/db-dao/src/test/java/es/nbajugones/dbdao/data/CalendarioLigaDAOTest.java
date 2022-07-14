package es.nbajugones.dbdao.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by iblanco on 08/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional

public class CalendarioLigaDAOTest {
	@Autowired
	CalendarioLigaDAO calendarioLigaDAO;

	@Test
	public void getPastGames() throws Exception {
		assertFalse(calendarioLigaDAO.getPastGames("BRO").isEmpty());
	}

	@Test
	public void getFutureGames() throws Exception {
		assertTrue(calendarioLigaDAO.getFutureGames("BRO").isEmpty());
	}

}