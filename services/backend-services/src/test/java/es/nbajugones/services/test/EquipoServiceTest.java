package es.nbajugones.services.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.services.EquipoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-backend-services.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class EquipoServiceTest {

	@Autowired
	EquipoService equipoService;
	
	@Test
	public void test(){
		try{
			EquipoDTO equipo = equipoService.getEquipo("GSW");
			Assert.assertTrue(equipo!=null);
			Assert.assertTrue(equipo.getPlantilla().size()>1);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
}
