
package es.nbajugones.dbdao.test;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dbdao.data.EquipoDAO;
import es.nbajugones.dbdao.data.JugadoresDAO;
import es.nbajugones.dto.entities.Equipo;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.exception.dbdao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class JugadoresDAOTest {

	@Autowired
	JugadoresDAO jugadoresDAO;
	
	@Autowired
	EquipoDAO equipoDAO;
	
	@Test
	public void testGetPlayer(){
		Jugadores jugador = jugadoresDAO.getById(1000);
		Assert.assertTrue(jugador.getJugador().equals("Austin Rivers"));
	}
	
	@Test
	public void testGetTop5FA(){
		Assert.assertTrue(jugadoresDAO.getTop5FA("G").size()>0);
		Assert.assertTrue(jugadoresDAO.getTop5FA("F").size()>0);
		Assert.assertTrue(jugadoresDAO.getTop5FA("C").size()>0);
	}
	
	@Test
	public void testGetFA(){
		Assert.assertTrue(jugadoresDAO.getAllFA().size()>0);
	}
	
	@Test
	public void testGetAll(){
		List<Jugadores> list =jugadoresDAO.getAll();
		Assert.assertTrue(list.size()>0);
		Assert.assertTrue(list.get(0).getEquipo()!=null);
	}
	
	@Test
	public void testOperaciones() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		Assert.assertTrue(j.getIdJugador()>0);
		int id = j.getIdJugador();
		jugadoresDAO.ficharFA("BRO", id, "0.7", "3");
		j = jugadoresDAO.getById(id);
		Equipo e = equipoDAO.getById("BRO");
		Assert.assertTrue(j.getYears().equals("3"));
		Assert.assertTrue(e.checkPlayer(id));
		jugadoresDAO.trade("CLE", id);
		e = equipoDAO.getById("CLE");
		Assert.assertTrue(e.checkPlayer(id));
		Assert.assertTrue(!equipoDAO.getById("BRO").checkPlayer(id));
		jugadoresDAO.cut("CLE", id, 0);
		j = jugadoresDAO.getById(id);
		Assert.assertTrue(j.getYears().equals("-"));
		Assert.assertTrue(!equipoDAO.getById("CLE").checkPlayer(id));
	}
	
}
