
package es.nbajugones.dbdao.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dbdao.data.LogDAO;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class LogDAOTest {

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	LogDAO logDAO;



	@Test
	public void testCut() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		jugadoresDAO.ficharFA("BRO", id, "0.7", "3", "2016-07-31");
		Assert.assertTrue(logDAO.cut("BRO", id).getIdAccion()>0);
	}

	@Test
	public void testFicharRenovaciones() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		Assert.assertTrue(logDAO.ficharRenovaciones(id, "BRO", 0.32, "2").getIdAccion()>0);
	}

	@Test
	public void testNoRenovar() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		Assert.assertTrue(logDAO.noRenovar(id, "BRO").getIdAccion()>0);
	}

	@Test
	public void testNoRenovar2() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		Assert.assertTrue(logDAO.noRenovar2(id, "BRO").getIdAccion()>0);
	}

	@Test
	public void testRenovar() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		Assert.assertTrue(logDAO.renovar(id, "BRO", 0.32, "2").getIdAccion()>0);
	}



}
