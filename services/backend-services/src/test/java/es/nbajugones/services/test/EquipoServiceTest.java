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

import es.nbajugones.dto.CalendarioDTO;
import es.nbajugones.dto.DerechoDTO;
import es.nbajugones.dto.EquipoDTO;
import es.nbajugones.dto.KeyValue;
import es.nbajugones.dto.LogDTO;
import es.nbajugones.services.EquipoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-backend-services.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class EquipoServiceTest {

	@Autowired
	EquipoService equipoService;
	
	@Test
	public void testGetEquipo(){
		try{
			EquipoDTO equipo = equipoService.getEquipo("GSW");
			Assert.assertTrue(equipo!=null);
			Assert.assertTrue(equipo.getPlantilla().size()>1);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testGetEquipos(){
		try{
			List<KeyValue> equipos = equipoService.getEquipos();
			Assert.assertTrue(equipos.size()>1);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testGetCalendario(){
		try{
			List<CalendarioDTO> calendario = equipoService.getCalendario("ATL");
			Assert.assertTrue(calendario.size()>1);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testGetDerechos(){
		try{
			List<DerechoDTO> calendario = equipoService.getDerechos("WAS");
			Assert.assertTrue(calendario.size()>1);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testGetLog(){
		try{
			List<LogDTO> log = equipoService.getLog("DEN");
			Assert.assertTrue(log.size()>1);
			Assert.assertTrue(log.get(0).getFecha()!=null);
		} catch (Exception e){
			Assert.fail();
		}
	}
	
}
