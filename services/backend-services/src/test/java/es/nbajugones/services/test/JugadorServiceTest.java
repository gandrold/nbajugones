package es.nbajugones.services.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dto.JugadorDTO;
import es.nbajugones.exception.service.ServiceException;
import es.nbajugones.services.JugadorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:backend-services.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class JugadorServiceTest {

	@Autowired
	JugadorService jugadorService;



	@Test
	public void testGetFA() throws ServiceException{
		Assert.assertTrue(jugadorService.getAllFA().size()>0);
	}

	@Test
	public void testGetAll() throws ServiceException{
		List<JugadorDTO> list =jugadorService.getAll();
		Assert.assertTrue(list.size()>0);
		Assert.assertTrue(list.get(0).getEquipo()!=null);
	}

}
