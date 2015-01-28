
package es.nbajugones.dbdao.test;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.exception.dbdao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class EquipoDAOTest {

	@Autowired
	EquipoDAO equipoDAO;
	
	@Test
	public void test(){
		Equipo equipo = equipoDAO.getById("BOS");
		Assert.assertTrue(equipo.getCalendarioLigas1().size()>0);
		Assert.assertTrue(equipo.getDerechos().size()>0);
		Assert.assertTrue(equipo.getHistorico().size()>0);
		Assert.assertTrue(equipo.getRondas().size()>0);
		Assert.assertTrue(equipo.getLog().size()>0);
		try {
			Assert.assertTrue(equipoDAO.getJugadores(equipo).size()>0);
		} catch (DaoException e) {			
			e.printStackTrace();
			Assert.fail();
		}
	}
}
