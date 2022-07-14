
package es.nbajugones.dbdao.test;


import java.util.Calendar;

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
import es.nbajugones.dbdao.data.RenovacionesDAO;
import es.nbajugones.dto.entities.Jugadores;
import es.nbajugones.dto.entities.Renovacione;
import es.nbajugones.dto.entities.pk.RenovacionePK;
import es.nbajugones.exception.dbdao.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class RenovacionesDAOTest {

	@Autowired
	JugadoresDAO jugadoresDAO;

	@Autowired
	EquipoDAO equipoDAO;

	@Autowired
	RenovacionesDAO renovacionesDAO;

	@Test
	public void testGet() throws DaoException{
		Assert.assertTrue(!renovacionesDAO.get(2015, 1).isEmpty());
	}

	@Test
	public void testRenovar() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		jugadoresDAO.ficharFA("BRO", id, "0.7", "-", "2016-07-31");
		Renovacione r = new Renovacione();
		RenovacionePK pk = new RenovacionePK();
		pk.setIdJugador(id);
		pk.setYear(Calendar.getInstance().get(Calendar.YEAR));
		r.setId(pk);
		r.setIdEquipoProp("BRO");
		renovacionesDAO.saveOrUpdateEntity(r, null);
		Assert.assertTrue(renovacionesDAO.getById(pk).getIdEquipoProp().equals("BRO"));
		renovacionesDAO.renovacionTemp("ATL", 2, 3, id, Calendar.getInstance().get(Calendar.YEAR));
		Assert.assertTrue(renovacionesDAO.getById(pk).getIdEquipoGanador().equals("ATL"));
		renovacionesDAO.renovar(id, "ATL", 2, 3);
		Assert.assertTrue(renovacionesDAO.getById(pk).getRenueva().equals("FICHADO"));
		Assert.assertTrue(!equipoDAO.getById("BRO").checkPlayer(id));
		Assert.assertTrue(equipoDAO.getById("ATL").checkPlayer(id));
	}

	@Test
	public void testRenovarFichar() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		jugadoresDAO.ficharFA("BRO", id, "0.7", "-", "2016-07-31");
		Renovacione r = new Renovacione();
		RenovacionePK pk = new RenovacionePK();
		pk.setIdJugador(id);
		pk.setYear(Calendar.getInstance().get(Calendar.YEAR));
		r.setId(pk);
		r.setIdEquipoProp("BRO");
		renovacionesDAO.saveOrUpdateEntity(r, null);
		Assert.assertTrue(renovacionesDAO.getById(pk).getIdEquipoProp().equals("BRO"));
		renovacionesDAO.renovacionTemp("ATL", 2, 3, id, Calendar.getInstance().get(Calendar.YEAR));
		Assert.assertTrue(renovacionesDAO.getById(pk).getIdEquipoGanador().equals("ATL"));
		renovacionesDAO.renovar(id, "BRO", 2, 3);
		Assert.assertTrue(renovacionesDAO.getById(pk).getRenueva().equals("RENOVADO"));
		Assert.assertTrue(equipoDAO.getById("BRO").checkPlayer(id));
		Assert.assertTrue(!equipoDAO.getById("ATL").checkPlayer(id));
	}

	@Test
	public void testNoRenovar() throws DaoException{
		Jugadores j = jugadoresDAO.crearJugador("Perico de los palotes", "FC");
		int id = j.getIdJugador();
		jugadoresDAO.ficharFA("BRO", id, "0.7", "-", "2016-07-31");
		Renovacione r = new Renovacione();
		RenovacionePK pk = new RenovacionePK();
		pk.setIdJugador(id);
		pk.setYear(Calendar.getInstance().get(Calendar.YEAR));
		r.setId(pk);
		r.setIdEquipoProp("BRO");
		renovacionesDAO.saveOrUpdateEntity(r, null);
		Assert.assertTrue(renovacionesDAO.getById(pk).getIdEquipoProp().equals("BRO"));
		renovacionesDAO.noRenovar(id, "BRO");
		Assert.assertTrue(renovacionesDAO.getById(pk).getRenueva().equals("FA"));
		Assert.assertTrue(!equipoDAO.getById("BRO").checkPlayer(id));
	}

}
