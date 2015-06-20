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
import es.nbajugones.dto.EvaluacionDTO;
import es.nbajugones.dto.KeyValue;
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
	public void testGetById() {
		Equipo equipo = equipoDAO.getById("BOS");
		Assert.assertTrue(equipo.getCalendarioLigas1().size() > 0);
		Assert.assertTrue(equipo.getDerechos().size() > 0);
		Assert.assertTrue(equipo.getHistorico().size() > 0);
		Assert.assertTrue(equipo.getRondas().size() > 0);
		Assert.assertTrue(equipo.getLog().size() > 0);
	}

	@Test
	public void testGetAll() {
		try {
			List<KeyValue> equipos = equipoDAO.getEquipos();
			Assert.assertTrue(equipos.size() > 0);
			Assert.assertTrue(equipos.get(0).getKey() != null);
		} catch (DaoException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testEvaluar() throws DaoException{
		List<EvaluacionDTO> evaluacion = equipoDAO.evaluar();
		Assert.assertTrue(evaluacion.size() > 0);
		Assert.assertTrue(evaluacion.get(0).getEquipo() != null);
	}
}
