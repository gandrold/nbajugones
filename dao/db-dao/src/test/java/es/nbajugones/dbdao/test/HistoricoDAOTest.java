package es.nbajugones.dbdao.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.HistoricoDAO;
import es.nbajugones.exception.dbdao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class HistoricoDAOTest {

	@Autowired
	HistoricoDAO dao;

	@Test
	public void testGetRonda() throws DaoException{
		Assert.assertTrue(!dao.getTemporada("2014-15").isEmpty());
	}


}
